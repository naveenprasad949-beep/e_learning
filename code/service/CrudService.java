package com.learnhub.service;

import java.util.List;

/** Polymorphic contract implemented by all six business modules. */
public interface CrudService<D> {
  List<D> list();

  D get(Long id);

  D create(D dto);

  D update(Long id, D dto);

  void delete(Long id);
}
