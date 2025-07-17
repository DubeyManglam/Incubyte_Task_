import axios from "axios";

const BASE_URL = "http://localhost:8080/sweetshop/api/sweet";

// GET all sweets
export const getSweetList = () => axios.get(`${BASE_URL}/getAllSweets`);

// POST add sweet
export const addSweet = (sweet) => axios.post(`${BASE_URL}/add`, sweet);

// DELETE sweet by id
export const deleteSweetById = (id) => axios.delete(`${BASE_URL}/delete/${id}`);

// SEARCH by name
export const searchSweetByName = (name) =>
  axios.get(`${BASE_URL}/search/byName/${name}`);

// SEARCH by category
export const searchSweetByCategory = (category) =>
  axios.get(`${BASE_URL}/search/byCategory/${category}`);

// SEARCH by price range
export const searchSweetsByPriceRange = (min, max) =>
  axios.get(`${BASE_URL}/search/by-price?minPrice=${min}&maxPrice=${max}`);

// SORT by price ascending
export const sortSweetsByPriceAsc = () =>
  axios.get(`${BASE_URL}/sort/price-asc`);

// SORT by price descending
export const sortSweetsByPriceDesc = () =>
  axios.get(`${BASE_URL}/sort/price-desc`);

// SORT by quantity ascending
export const sortSweetsByQuantityAsc = () =>
  axios.get(`${BASE_URL}/sort/quantity-asc`);

// SORT by quantity descending
export const sortSweetsByQuantityDesc = () =>
  axios.get(`${BASE_URL}/sort/quantity-desc`);

// POST purchase sweet with quantity param
export const purchaseSweet = (id, quantity) =>
  axios.post(`${BASE_URL}/purchase/${id}?quantity=${quantity}`);

// POST restock sweet with quantity param
export const restockSweet = (id, quantity) =>
  axios.post(`${BASE_URL}/restock/${id}?quantity=${quantity}`);
