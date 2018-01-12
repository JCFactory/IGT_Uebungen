package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Database;
import app.EntityManager;
import dao.Dao;
import entity.Customer;
import entity.District;
import entity.Item;
import entity.ItemOrder;
import entity.Warehouse;

@RestController
public class BenchmarkController {

	private EntityManager entityManager;
	private Dao<Customer> customerDao;
	private Dao<District> districtDao;
	private Dao<Item> itemDao;
	private Dao<ItemOrder> itemOrderDao;
	private Dao<Warehouse> warehouseDao;

	public BenchmarkController() {
		this.entityManager = new EntityManager(Database.MYSQL);
		this.customerDao = new Dao<Customer>(this.entityManager, Customer.class);
		this.districtDao = new Dao<District>(this.entityManager, District.class);
		this.itemDao = new Dao<Item>(this.entityManager, Item.class);
		this.itemOrderDao = new Dao<ItemOrder>(this.entityManager, ItemOrder.class);
		this.warehouseDao = new Dao<Warehouse>(this.entityManager, Warehouse.class);
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> issueNewOrder(@RequestParam Customer customer, @RequestParam List<Item> items) {

		try {

			ItemOrder itemOrder;

			/**
			 * SAVE INCOMING ORDER IF IT DOES NOT EXIST IN THE DATABASE YET
			 */
			if (this.itemOrderDao.find(items.get(0).getItemOrder().getIo_id()) == null) {
				itemOrder = new ItemOrder();
				itemOrder.setCustomer(customer);
				this.itemOrderDao.save(itemOrder);
			} else {
				itemOrder = this.itemOrderDao.find(items.get(0).getItemOrder().getIo_id());
			}

			/**
			 * CHECK IF ITEMS ARE AVAILABLE IN STOCK
			 */
			List<Warehouse> allWarehouses = warehouseDao.findAll();
			List<Item> missingItems = new ArrayList<Item>();

			for (Item item : items) {
				boolean exists = false;
				for (Warehouse warehouse : allWarehouses) {
					if (warehouse.getStock().containsKey(item.getI_name())) {
						if (warehouse.getStock().get(item.getI_name()) == 0) {
							missingItems.add(item);
						}
					}
				}
			}

			if (missingItems.size() > 0) {
				JSONObject response = new JSONObject();
				response.put("message", "Order is not executable");
				response.put("status", false);
				response.put("itemOrder", itemOrder);
				response.put("items", items);
				response.put("missingItems", missingItems);

				return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
			}

			/**
			 * CHECK IF ITEMS ARE AVAILABLE IN WAREHOUSE OF CUSTOMER
			 */
			Warehouse warehouse = customer.getDistrict().getWarehouse();

			for (Item item : items) {
				if (warehouse.getStock().containsKey(item.getI_name())) {
					if (warehouse.getStock().get(item.getI_name()) == 0) {
						missingItems.add(item);
					} else {
						warehouse.getStock().put(item.getI_name(), -1);
					}
				}
			}

			/**
			 * IF ITEMS ARE MISSING ORDER ITEMS FROM OTHER WAREHOUSE
			 */
			if (missingItems.size() > 0) {
				for (Item missingItem : missingItems) {
					for (Warehouse otherWarehouse : allWarehouses) {
						if (otherWarehouse.getW_id() != warehouse.getW_id()) {
							if (warehouse.getStock().containsKey(missingItem.getI_name())) {
								if (warehouse.getStock().get(missingItem.getI_name()) > 0) {
									warehouse.getStock().put(missingItem.getI_name(), -1);
									// send missing item to warehouse of customer
								}
							}
						}
					}
				}
			}

			itemOrder.setIo_sum(calculatePrize(items));

			JSONObject response = new JSONObject();
			response.put("message", "Your order has benn issued.");
			response.put("status", true);
			response.put("itemOrder", itemOrder);
			response.put("items", items);
			response.put("missingItems", missingItems);

			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);

		} catch (Exception e) {
			JSONObject response = new JSONObject();
			response.put("message", e.getMessage());
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Calculates sum of items in order.
	 */
	private float calculatePrize(List<Item> items) {
		float totalPrize = 0;

		for (Item item : items) {
			totalPrize += item.getI_price();
		}

		if (totalPrize >= 1000) {
			totalPrize *= 0.83;
		}

		return totalPrize;
	}
}