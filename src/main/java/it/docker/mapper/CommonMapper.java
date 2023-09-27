package it.docker.mapper;

public interface CommonMapper<E, D> {
	
	D serializza(E entity);
	
	E deserializza(D dto);

}
