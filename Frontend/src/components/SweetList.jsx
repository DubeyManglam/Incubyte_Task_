import {
  purchaseSweet,
  restockSweet,
  deleteSweetById,
} from "../service/ApiSweet.js";
import toast from "react-hot-toast";

const SweetList = ({ sweets, fetchAll }) => {
  const handlePurchase = async (id) => {
    const qty = prompt("Quantity to purchase:");
    if (!qty) return;
    try {
      await purchaseSweet(id, qty);
      fetchAll();
      toast.success("Purchased");
    } catch {
      toast.error("Failed");
    }
  };

  const handleRestock = async (id) => {
    const qty = prompt("Quantity to restock:");
    if (!qty) return;
    try {
      await restockSweet(id, qty);
      fetchAll();
      toast.success("Restocked");
    } catch {
      toast.error("Failed");
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteSweetById(id);
      fetchAll();
      toast.success("Deleted");
    } catch {
      toast.error("Failed");
    }
  };

  return (
    <div className="container mt-4">
      <div className="row">
        {sweets.map((sweet) => (
          <div className="col-md-4 mb-4" key={sweet.id}>
            <div className="card shadow-sm h-100">
              <div className="card-body">
                <h5 className="card-title fw-bold">{sweet.name}</h5>
                <p className="card-text">Category: {sweet.category}</p>
                <p className="card-text">Price: ₹{sweet.price}</p>
                <p className="card-text">Stock: {sweet.quantityInStock}</p>
                <div className="d-flex justify-content-between">
                  <button
                    className="btn btn-success btn-sm"
                    onClick={() => handlePurchase(sweet.id)}
                  >
                    Purchase
                  </button>
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => handleRestock(sweet.id)}
                  >
                    Restock
                  </button>
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => handleDelete(sweet.id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SweetList;
