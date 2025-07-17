import { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import SweetList from "../components/SweetList";
import AddSweetPopup from "../components/AddSweetPopup";
import {
  getSweetList,
  searchSweetByName,
  searchSweetByCategory,
  searchSweetsByPriceRange,
  sortSweetsByPriceAsc,
  sortSweetsByPriceDesc,
  sortSweetsByQuantityAsc,
  sortSweetsByQuantityDesc,
} from "../service/ApiSweet.js";
import toast from "react-hot-toast";

const HomePage = () => {
  const [sweets, setSweets] = useState([]);
  const [showAddPopup, setShowAddPopup] = useState(false);

  const fetchAll = async () => {
    try {
      const res = await getSweetList();
      setSweets(res.data);
    } catch {
      toast.error("Failed to fetch sweets");
    }
  };

  const handleSearch = async (type, input) => {
    try {
      if (type === "name") {
        const res = await searchSweetByName(input);
        setSweets(res.data);
      } else if (type === "category") {
        const res = await searchSweetByCategory(input);
        setSweets(res.data);
      } else if (type === "range") {
        const [min, max] = input.split(",");
        if (!min || !max) {
          toast.error("Enter min,max");
          return;
        }
        const res = await searchSweetsByPriceRange(min, max);
        setSweets(res.data);
      }
    } catch {
      toast.error("Search failed");
    }
  };

  const handleSort = async (sortType) => {
    try {
      let res;
      switch (sortType) {
        case "priceAsc":
          res = await sortSweetsByPriceAsc();
          break;
        case "priceDesc":
          res = await sortSweetsByPriceDesc();
          break;
        case "quantityAsc":
          res = await sortSweetsByQuantityAsc();
          break;
        case "quantityDesc":
          res = await sortSweetsByQuantityDesc();
          break;
        default:
          return fetchAll();
      }
      setSweets(res.data);
    } catch {
      toast.error("Sort failed");
    }
  };

  useEffect(() => {
    fetchAll();
  }, []);

  return (
    <>
      <Navbar
        onSearch={handleSearch}
        onSort={handleSort}
        onAddSweet={() => setShowAddPopup(true)}
      />
      <SweetList sweets={sweets} fetchAll={fetchAll} />
      {showAddPopup && (
        <>
          <div
            className="position-fixed top-0 start-0 w-100 h-100 bg-dark"
            style={{ opacity: 0.5, zIndex: 1040 }}
          ></div>

          <AddSweetPopup
            onClose={() => setShowAddPopup(false)}
            onSweetAdded={fetchAll}
          />
        </>
      )}
    </>
  );
};

export default HomePage;
