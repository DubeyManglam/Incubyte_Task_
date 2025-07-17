import { useState } from "react";

const Navbar = ({ onSearch, onSort, onAddSweet }) => {
  const [searchType, setSearchType] = useState("name");
  const [searchInput, setSearchInput] = useState("");

  const handleSearch = () => {
    if (!searchInput) return;
    onSearch(searchType, searchInput);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary px-4 py-3">
      <div className="d-flex align-items-center">
        <span className="navbar-brand fw-bold fs-4 me-2">Sweet Shop</span>
        <button className="btn btn-warning btn-sm ms-2" onClick={onAddSweet}>
          ➕ Add Sweet
        </button>
      </div>

      <div className="ms-auto d-flex align-items-center flex-wrap gap-2">
        <select
          className="form-select form-select-sm"
          style={{ width: "150px" }}
          value={searchType}
          onChange={(e) => setSearchType(e.target.value)}
        >
          <option value="name">By Name</option>
          <option value="category">By Category</option>
          <option value="range">By Price Range</option>
        </select>

        <input
          type="text"
          className="form-control form-control-sm"
          placeholder="Search..."
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
          style={{ width: "180px" }}
        />

        <button className="btn btn-light btn-sm" onClick={handleSearch}>
          Search
        </button>

        <select
          className="form-select form-select-sm"
          style={{ width: "130px" }}
          onChange={(e) => onSort(e.target.value)}
        >
          <option value="">Sort By</option>
          <option value="priceAsc">Price ↑</option>
          <option value="priceDesc">Price ↓</option>
          <option value="quantityAsc">Qty ↑</option>
          <option value="quantityDesc">Qty ↓</option>
        </select>
      </div>
    </nav>
  );
};

export default Navbar;
