import React, { useEffect, useState } from "react";
import api from "../api";

export default function RechargeForm() {
  const [members, setMembers] = useState([]);
  const [history, setHistory] = useState([]);
  const [form, setForm] = useState({ memberId: "", amount: "" });
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchMembers();
    fetchHistory();
  }, []);

  async function fetchMembers() {
    try {
      const res = await api.get("/members");
      setMembers(res.data?.data || []);
    } catch (err) {
      console.error(err);
    }
  }

  async function fetchHistory() {
    try {
      const res = await api.get("/recharges");
      setHistory(res.data?.data || []);
    } catch (err) {
      console.error(err);
    }
  }

  function onChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function onSubmit(e) {
    e.preventDefault();
    setLoading(true);
    try {
      await api.post("/recharges", {
        memberId: Number(form.memberId),
        amount: Number(form.amount),
      });
      alert("Recharged successfully");
      setForm({ memberId: "", amount: "" });
      fetchHistory();
    } catch (err) {
      alert(
        "Recharge failed: " +
          (err.response?.data?.message || err.message || err)
      );
    } finally {
      setLoading(false);
    }
  }

  return (
    <div
      style={{
        fontFamily: "Segoe UI, sans-serif",
        backgroundColor: "#f4f6f8",
        minHeight: "100vh",
        padding: "30px",
      }}
    >
      <h2 style={{ color: "#0d47a1", marginBottom: 20 }}>💰 Recharge Wallet</h2>

      {/* ✅ Added header for Recharge Details */}
      <h3
        style={{
          fontWeight: "bold",
          color: "#333",
          marginBottom: 30,
          borderBottom: "3px solid #1976d2",
          display: "inline-block",
          paddingBottom: 10,
        }}
      >
        Recharge Details
      </h3>

      <form
        onSubmit={onSubmit}
        style={{
          backgroundColor: "#ffffff",
          padding: 20,
          borderRadius: 8,
          boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
          maxWidth: 400,
          marginBottom: 30,
          marginTop: 10,
        }}
      >
        <div style={{ marginBottom: 16 }}>
          <label
            style={{ display: "block", fontWeight: "bold", marginBottom: 6 }}
          >
            Member
          </label>
          <select
            name="memberId"
            value={form.memberId}
            onChange={onChange}
            required
            style={{
              width: "100%",
              padding: 10,
              borderRadius: 4,
              border: "1px solid #ccc",
              outline: "none",
            }}
          >
            <option value="">Select member</option>
            {members.map((m) => (
              <option key={m.memberId} value={m.memberId}>
                {m.name} (ID:{m.memberId})
              </option>
            ))}
          </select>
        </div>

        <div style={{ marginBottom: 16 }}>
          <label
            style={{ display: "block", fontWeight: "bold", marginBottom: 6 }}
          >
            Amount
          </label>
          <input
            name="amount"
            value={form.amount}
            onChange={onChange}
            type="number"
            step="0.01"
            required
            style={{
              width: "100%",
              padding: 10,
              borderRadius: 4,
              border: "1px solid #ccc",
              outline: "none",
            }}
          />
        </div>

        <button
          type="submit"
          disabled={loading}
          style={{
            backgroundColor: loading ? "#90caf9" : "#1976d2",
            color: "white",
            padding: "10px 16px",
            border: "none",
            borderRadius: 4,
            cursor: loading ? "not-allowed" : "pointer",
            width: "100%",
            fontWeight: "bold",
          }}
        >
          {loading ? "Processing..." : "Recharge"}
        </button>
      </form>

      <h3 style={{ color: "#333", marginBottom: 10 }}>📜 Recharge History</h3>
      <div
        style={{
          overflowX: "auto",
          backgroundColor: "#fff",
          padding: 10,
          borderRadius: 8,
          boxShadow: "0 2px 8px rgba(0,0,0,0.05)",
        }}
      >
        <table
          width="100%"
          cellPadding="10"
          style={{ borderCollapse: "collapse", minWidth: 600 }}
        >
          <thead style={{ backgroundColor: "#1565c0", color: "white" }}>
            <tr>
              <th>ID</th>
              <th>Member ID</th>
              <th>Amount</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {history.length === 0 ? (
              <tr>
                <td
                  colSpan="4"
                  style={{ textAlign: "center", color: "#999", padding: 20 }}
                >
                  No recharges yet
                </td>
              </tr>
            ) : (
              history.map((r, index) => (
                <tr
                  key={r.rechargeId}
                  style={{
                    backgroundColor: index % 2 === 0 ? "#f9f9f9" : "#ffffff",
                    borderBottom: "1px solid #eee",
                  }}
                >
                  <td>{r.rechargeId}</td>
                  <td>{r.memberId}</td>
                  <td>${Number(r.amount).toFixed(2)}</td>
                  <td>{new Date(r.rechargeDate).toLocaleString()}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}