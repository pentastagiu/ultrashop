package com.pentalog.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.sc.dao.StockDAO;
import com.pentalog.sc.model.Stock;


/**
 * implements method from stockService interface
 *
 */
@Service("stockService")
public class StockServiceImpl implements StockService{

	
	@Autowired
	private StockDAO stockDao;
	
	/**
	 * @see {@link StockService.getStocks}
	 */
	@Override
	public List<Stock> getStocks() {
		return stockDao.findAll();
	}

	/**
	 * @see {@link StockService.create}
	 */
	@Override
	@Transactional
	public Stock create(Stock stock) {
		return stockDao.save(stock);
	}

	/**
	 * @see {@link StockService.findById}
	 */
	@Override
	@Transactional
	public Stock findById(int id) {
		return stockDao.findOne(id);
	}

	/**
	 * @see {@link StockService.update}
	 */
	@Override
	@Transactional
	public Stock update(Stock stock) {
		Stock stockToUpdate = stockDao.findOne(stock.getId());
		if(stockToUpdate != null){
			stockToUpdate.setProduct(stock.getProduct());
			stockToUpdate.setAmount(stock.getAmount());
			stockToUpdate.setSupplier(stock.getSupplier());
		}
		return stockToUpdate;
	}

	/**
	 * @see {@link StockService.delete}
	 */
	@Override
	@Transactional
	public Stock delete (Stock stock) {
		Stock stockToDelete = new Stock();
		stockToDelete.setId(stock.getId());
		stockToDelete.setAmount(stock.getAmount());
		stockToDelete.setProduct(stock.getProduct());
		stockToDelete.setSupplier(stock.getSupplier());
		stockDao.delete(stock);
		return stockToDelete;
	}

	
	/**
	 * @see {@link StockService.findBySupplierId}}
	 */
	@Override
	@Transactional
	public List<Stock> findBySupplierId(int id) {
		return stockDao.findBySupplierId(id);
	}

    @Override
    public long count() {
        return stockDao.count();
    }

    @Override
    public List<Stock> readStocksByPage(int pageIndex, int offset) {
        PageRequest request = new PageRequest(pageIndex, offset);
        Page<Stock> page = stockDao.findAll(request);
        return page.getContent();
    }

    @Override
    public List<Stock> findByProductId(int id) {
        return stockDao.findByProductId(id);
    }

}


