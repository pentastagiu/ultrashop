package com.pentalog.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.us.dao.SupplierDAO;
import com.pentalog.us.model.Supplier;
import com.pentalog.us.service.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService{
	
	
	@Autowired
	SupplierDAO supplierDao;
	
	
	public List<Supplier> getSuppliers() {
		return supplierDao.findAll();
	}

	/**
	 * @see {link ProductService.findById}
	 */
	@Override
	public Supplier findById(int id) {
		return supplierDao.findOne(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Supplier create(Supplier supplier) {
		return supplierDao.save(supplier);
	}

	/**
	 * {@inherit doc}
	 */
	@Override
	public Supplier getSupplierByExternalId(int id) {
		return supplierDao.findByExternalId(id);
	}

	/**
	 * {@inherit doc}
	 */
	@Override
	public Supplier getSupplierById(int id) {
		return supplierDao.findById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Supplier update(Supplier supplier) {
		Supplier supplierToUpdate = supplierDao.findByExternalId(supplier.getId());
		if (supplierToUpdate != null) {
			supplierToUpdate.setName(supplier.getName());
			supplierToUpdate.setEmail(supplier.getEmail());
			supplierToUpdate.setContactDetails(supplier.getContactDetails());
			supplierDao.save(supplierToUpdate);
		}
		return supplierToUpdate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Supplier delete(Supplier supplier) {
		Supplier supplierToDelete = new Supplier();
		supplierToDelete.setId(supplier.getId());
		supplierToDelete.setName(supplier.getName());
		supplierToDelete.setEmail(supplier.getEmail());
		supplierToDelete.setContactDetails(supplier.getContactDetails());
		supplierDao.delete(supplierToDelete);
		return supplierToDelete;
	}
	
	public List<Supplier> findByActive(Boolean active) {
		return supplierDao.findByActive(active);
	}

	@Override
	public long countByActive(Boolean active) {
		return supplierDao.countByActive(active);
	}

	@Override
	public List<Supplier> readSuppliersByPage(int pageIndex, int offset) {

		PageRequest request = new PageRequest(pageIndex, offset);
		Page<Supplier> page = supplierDao.findAll(request);
		return page.getContent();

	}


	@Override
	public void syncSuppliers(List<Supplier> scSuppliers) {
		for(Supplier s : scSuppliers){
			Supplier supplier = getSupplierByExternalId(s.getId());
			if(supplier != null){				
				update(supplier);
			}
			else{
				supplier = new Supplier();
				supplier.setExternalId(s.getId());
				supplier.setName(s.getName());
				supplier.setEmail(s.getEmail());
				supplier.setContactDetails(s.getContactDetails());
				supplier.setActive(s.isActive());
				create(supplier);
			}
			@SuppressWarnings("unused")
			boolean find = false;
			List<Supplier> usSupplier = getSuppliers();
			if(usSupplier.size() > scSuppliers.size()){
				for (Supplier sup : usSupplier) {
					for (Supplier sup2 : scSuppliers) {
						if(sup.getExternalId() == sup2.getId()){
							find = true;
						}
					}
					if(find=false){
						delete(sup);
					}
				}
			}
		}
	}
}
