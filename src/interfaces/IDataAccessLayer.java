package interfaces;

import java.util.List;

import type.CustomerContract;

public interface IDataAccessLayer<T> {
	
	public void insert(T contract);
	public void update(T contract);
	public void delete(T contract);
	public List<T> getList();
	public T getById(int id);
	//List<CustomerContract> getList();
	
}
