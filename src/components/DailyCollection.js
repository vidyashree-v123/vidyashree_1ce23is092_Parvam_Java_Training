import React, { useEffect, useState } from "react";
import { getDailyCollections, getDailyCollectionByDate } from "../api";

export default function DailyCollection() {
  const [collections, setCollections] = useState([]);
  const [date, setDate] = useState("");
  const [selected, setSelected] = useState(null);

  useEffect(() => {
    fetchCollections();
  }, []);

  async function fetchCollections() {
    try {
      const res = await getDailyCollections();
      setCollections(res.data?.data || []);
    } catch (err) {
      console.error(err);
      alert("Failed to fetch daily collections");
    }
  }

  async function fetchByDate() {
    if (!date) return;
    try {
      const res = await getDailyCollectionByDate(date);
      setSelected(res.data?.data || null);
    } catch (err) {
      alert("No data for this date or error: " + (err.message || err));
      setSelected(null);
    }
  }

  return (
    <div style={container}>
      <h2 style={heading}>📅 Daily Collections</h2>

      {/* Date filter */}
      <div style={filterSection}>
        <input
          type="date"
          value={date}
          onChange={(e) => setDate(e.target.value)}
          style={dateInput}
        />
        <button onClick={fetchByDate} style={filterButton}>
          🔍 Get by Date
        </button>
      </div>

      {/* Selected date summary */}
      {selected && (
        <div style={summaryBox}>
          <h3 style={subHeading}>Collection for {selected.collectionDate}</h3>
          <p><strong>Total Recharges:</strong> {selected.totalRecharges}</p>
          <p><strong>Total Spent:</strong> {selected.totalSpent}</p>
          <p>
            <strong>Net Collection:</strong>{" "}
            <span style={{
              color: selected.netCollection >= 0 ? "#2e7d32" : "#d32f2f",
              fontWeight: "bold"
            }}>
              {selected.netCollection}
            </span>
          </p>
        </div>
      )}

      {/* All collections table */}
      <h3 style={subHeading}>📊 All Daily Collections</h3>
      <div style={tableWrapper}>
        <table style={tableStyle}>
          <thead>
            <tr style={tableHeaderRow}>
              <th style={thTd}>Date</th>
              <th style={thTd}>Total Recharges</th>
              <th style={thTd}>Total Spent</th>
              <th style={thTd}>Net</th>
            </tr>
          </thead>
          <tbody>
            {collections.length === 0 ? (
              <tr>
                <td colSpan="4" style={noData}>No records</td>
              </tr>
            ) : (
              collections.map((c, idx) => (
                <tr
                  key={c.collectionId}
                  style={{
                    backgroundColor: idx % 2 === 0 ? "#f9f9f9" : "#ffffff",
                  }}
                >
                  <td style={thTd}>{c.collectionDate}</td>
                  <td style={thTd}>{c.totalRecharges}</td>
                  <td style={thTd}>{c.totalSpent}</td>
                  <td style={{
                    ...thTd,
                    color: c.netCollection >= 0 ? "#388e3c" : "#c62828",
                    fontWeight: "bold"
                  }}>
                    {c.netCollection}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

// ======================
// ✅ Styles
// ======================

const container = {
  fontFamily: "Segoe UI, sans-serif",
  backgroundColor: "#f0f4f8",
  padding: "30px",
  minHeight: "100vh",
};

const heading = {
  color: "#2c3e50",
  marginBottom: "20px",
};

const subHeading = {
  color: "#34495e",
  margin: "20px 0 10px",
};

const filterSection = {
  marginBottom: "20px",
};

const dateInput = {
  padding: "10px",
  borderRadius: "4px",
  border: "1px solid #ccc",
  fontSize: "16px",
};

const filterButton = {
  marginLeft: "10px",
  padding: "10px 16px",
  backgroundColor: "#1976d2",
  color: "#fff",
  border: "none",
  borderRadius: "4px",
  cursor: "pointer",
};

const summaryBox = {
  backgroundColor: "#ffffff",
  padding: "16px",
  borderRadius: "6px",
  boxShadow: "0 1px 6px rgba(0, 0, 0, 0.1)",
  maxWidth: "400px",
};

const tableWrapper = {
  overflowX: "auto",
  backgroundColor: "#ffffff",
  borderRadius: "8px",
  boxShadow: "0 1px 6px rgba(0, 0, 0, 0.1)",
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse",
};

const tableHeaderRow = {
  backgroundColor: "#1565c0",
  color: "#ffffff",
};

const thTd = {
  padding: "10px",
  textAlign: "left",
  borderBottom: "1px solid #ddd",
};

const noData = {
  textAlign: "center",
  padding: "20px",
  color: "#888",
};

