import { useState } from "react";
import toast from "react-hot-toast";
import { addSweet } from "../service/ApiSweet.js";

const AddSweetPopup = ({ onClose, onSweetAdded }) => {
  const [sweet, setSweet] = useState({
    name: "",
    category: "",
    price: "",
    quantityInStock: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addSweet(sweet);
      toast.success("Sweet added successfully");
      onSweetAdded(); // Refresh parent list
      onClose(); // Close popup
    } catch (err) {
      toast.error(err.response?.data?.message || "Failed to add sweet");
    }
  };

  return (
    <div
      className="position-fixed top-50 start-50 translate-middle shadow-lg bg-white border rounded p-4"
      style={{ zIndex: 1050, width: "400px" }}
    >
      <h4 className="mb-3 text-center">Add New Sweet</h4>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          className="form-control mb-3 fs-5"
          placeholder="Name"
          value={sweet.name || ""}
          onChange={(e) => setSweet({ ...sweet, name: e.target.value })}
          required
        />
        <input
          type="text"
          className="form-control mb-3 fs-5"
          placeholder="Category"
          value={sweet.category || ""}
          onChange={(e) => setSweet({ ...sweet, category: e.target.value })}
          required
        />
        <input
          type="number"
          className="form-control mb-3 fs-5"
          placeholder="Price"
          value={sweet.price || ""}
          onChange={(e) => setSweet({ ...sweet, price: e.target.value })}
          required
        />
        <input
          type="number"
          className="form-control mb-3 fs-5"
          placeholder="Quantity"
          value={sweet.quantityInStock || ""}
          onChange={(e) =>
            setSweet({ ...sweet, quantityInStock: e.target.value })
          }
          required
        />
        <div className="d-flex justify-content-between">
          <button
            type="button"
            className="btn btn-secondary px-4"
            onClick={onClose}
          >
            Cancel
          </button>
          <button type="submit" className="btn btn-primary px-4">
            Add Sweet
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddSweetPopup;
