import React, { useEffect, useState } from "react";
import api from "../api";

export default function TransactionManager() {
  const [transactions, setTransactions] = useState([]);
  const [members, setMembers] = useState([]);
  const [games, setGames] = useState([]);
  const [loading, setLoading] = useState(true);

  const [form, setForm] = useState({
    transactionId: null,
    memberId: "",
    gameId: "",
    playTimeHrs: "",
    cost: "",
    transactionDate: "",
  });

  useEffect(() => {
    fetchAll();
  }, []);

  async function fetchAll() {
    setLoading(true);
    try {
      const [tRes, mRes, gRes] = await Promise.all([
        api.get("/transactions"),
        api.get("/members"),
        api.get("/games"),
      ]);

      const txData = tRes.data?.data || tRes.data?.transactions || [];
      const memberData = mRes.data?.data || [];
      const gameData = gRes.data?.data || [];

      setTransactions(txData);
      setMembers(memberData);
      setGames(gameData);
    } catch (err) {
      alert("Error loading transactions: " + (err.message || err));
    } finally {
      setLoading(false);
    }
  }

  function onChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function onSubmit(e) {
    e.preventDefault();
    try {
      const payload = {
        memberId: Number(form.memberId),
        gameId: Number(form.gameId),
        playTimeHrs: Number(form.playTimeHrs),
        cost: Number(form.cost),
        transactionDate: form.transactionDate,
      };

      if (form.transactionId) {
        await api.put(`/transactions/${form.transactionId}`, payload);
        alert("Transaction updated successfully!");
      } else {
        await api.post("/transactions", payload);
        alert("Transaction added successfully!");
      }

      setForm({
        transactionId: null,
        memberId: "",
        gameId: "",
        playTimeHrs: "",
        cost: "",
        transactionDate: "",
      });

      fetchAll();
    } catch (err) {
      alert("Save failed: " + (err.response?.data?.message || err.message || err));
    }
  }

  async function handleEdit(tx) {
    setForm({
      transactionId: tx.transactionId,
      memberId: tx.memberId,
      gameId: tx.gameId,
      playTimeHrs: tx.playTimeHrs,
      cost: tx.cost,
      transactionDate: tx.transactionDate?.slice(0, 16) || "",
    });
  }

  async function handleDelete(id) {
    if (!window.confirm("Delete this transaction?")) return;
    try {
      await api.delete(`/transactions/${id}`);
      fetchAll();
    } catch (err) {
      alert("Delete failed: " + (err.message || err));
    }
  }

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>🎮 Transaction Manager</h2>

      {/* Transaction Form */}
      <form onSubmit={onSubmit} style={styles.formBox}>
        <h3 style={styles.sectionTitle}>
          {form.transactionId ? "Edit Transaction" : "Add New Transaction"}
        </h3>

        <div style={styles.formGroup}>
          <label style={styles.label}>Member</label>
          <select
            name="memberId"
            value={form.memberId}
            onChange={onChange}
            required
            style={styles.input}
          >
            <option value="">Select member</option>
            {members.map((m) => (
              <option key={m.memberId} value={m.memberId}>
                {m.name} (ID:{m.memberId})
              </option>
            ))}
          </select>
        </div>

        <div style={styles.formGroup}>
          <label style={styles.label}>Game</label>
          <select
            name="gameId"
            value={form.gameId}
            onChange={onChange}
            required
            style={styles.input}
          >
            <option value="">Select game</option>
            {games.map((g) => (
              <option key={g.gameId} value={g.gameId}>
                {g.gameName} (ID:{g.gameId})
              </option>
            ))}
          </select>
        </div>

        <div style={styles.formRow}>
          <div style={{ flex: 1 }}>
            <label style={styles.label}>Play Hours</label>
            <input
              type="number"
              name="playTimeHrs"
              value={form.playTimeHrs}
              onChange={onChange}
              step="0.1"
              required
              style={styles.input}
            />
          </div>
          <div style={{ flex: 1, marginLeft: 10 }}>
            <label style={styles.label}>Cost</label>
            <input
              type="number"
              name="cost"
              value={form.cost}
              onChange={onChange}
              step="0.01"
              required
              style={styles.input}
            />
          </div>
        </div>

        <div style={styles.formGroup}>
          <label style={styles.label}>Date & Time</label>
          <input
            type="datetime-local"
            name="transactionDate"
            value={form.transactionDate}
            onChange={onChange}
            required
            style={styles.input}
          />
        </div>

        <div style={{ marginTop: 15 }}>
          <button type="submit" style={styles.submitButton}>
            {form.transactionId ? "Update" : "Add"} Transaction
          </button>
          {form.transactionId && (
            <button
              type="button"
              onClick={() =>
                setForm({
                  transactionId: null,
                  memberId: "",
                  gameId: "",
                  playTimeHrs: "",
                  cost: "",
                  transactionDate: "",
                })
              }
              style={styles.cancelButton}
            >
              Cancel
            </button>
          )}
        </div>
      </form>

      {/* Transaction History Box */}
      <div style={styles.historyBox}>
        <h3 style={styles.sectionTitle}>Transaction History</h3>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <table style={styles.table}>
            <thead>
              <tr>
                <th>ID</th>
                <th>Member</th>
                <th>Game</th>
                <th>Play Hrs</th>
                <th>Cost</th>
                <th>Date</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {transactions.length === 0 ? (
                <tr>
                  <td colSpan="7" style={{ textAlign: "center" }}>
                    No transactions found
                  </td>
                </tr>
              ) : (
                transactions.map((tx) => (
                  <tr key={tx.transactionId}>
                    <td>{tx.transactionId}</td>
                    <td>{members.find((m) => m.memberId === tx.memberId)?.name ?? tx.memberId}</td>
                    <td>{games.find((g) => g.gameId === tx.gameId)?.gameName ?? tx.gameId}</td>
                    <td>{tx.playTimeHrs}</td>
                    <td>₹{tx.cost}</td>
                    <td>{new Date(tx.transactionDate).toLocaleString()}</td>
                    <td>
                      <button style={styles.editButton} onClick={() => handleEdit(tx)}>
                        Edit
                      </button>
                      <button
                        style={styles.deleteButton}
                        onClick={() => handleDelete(tx.transactionId)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

// ✅ Styling
const styles = {
  container: {
    fontFamily: "Poppins, sans-serif",
    backgroundColor: "#f3f5f9",
    minHeight: "100vh",
    padding: "30px 40px",
  },
  title: {
    textAlign: "center",
    color: "#1a237e",
    marginBottom: 30,
  },
  formBox: {
    backgroundColor: "#ffffff",
    padding: 25,
    borderRadius: 10,
    boxShadow: "0 3px 10px rgba(0,0,0,0.1)",
    maxWidth: 600,
    margin: "0 auto",
    marginBottom: 30,
  },
  formGroup: { marginBottom: 15 },
  formRow: { display: "flex", gap: 10 },
  label: { fontWeight: "bold", color: "#333", marginBottom: 6, display: "block" },
  input: {
    width: "100%",
    padding: 10,
    borderRadius: 6,
    border: "1px solid #ccc",
    outline: "none",
  },
  submitButton: {
    backgroundColor: "#1976d2",
    color: "white",
    padding: "10px 16px",
    border: "none",
    borderRadius: 6,
    cursor: "pointer",
    fontWeight: "bold",
    marginRight: 10,
  },
  cancelButton: {
    backgroundColor: "#e0e0e0",
    color: "#333",
    padding: "10px 16px",
    border: "none",
    borderRadius: 6,
    cursor: "pointer",
  },
  sectionTitle: {
    textAlign: "center",
    fontWeight: "bold",
    color: "#0d47a1",
    borderBottom: "3px solid #1976d2",
    display: "inline-block",
    paddingBottom: 5,
    marginBottom: 20,
  },
  historyBox: {
    backgroundColor: "#fff",
    padding: 20,
    borderRadius: 10,
    boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
    border: "1px solid #ddd",
  },
  table: {
    width: "100%",
    borderCollapse: "collapse",
  },
  editButton: {
    backgroundColor: "#0288d1",
    color: "white",
    padding: "6px 10px",
    border: "none",
    borderRadius: 4,
    cursor: "pointer",
    marginRight: 5,
  },
  deleteButton: {
    backgroundColor: "#d32f2f",
    color: "white",
    padding: "6px 10px",
    border: "none",
    borderRadius: 4,
    cursor: "pointer",
  },
};