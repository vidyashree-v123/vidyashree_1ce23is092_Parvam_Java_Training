import React, { useEffect, useState } from "react";
import api from "../api";
import { Link, useNavigate } from "react-router-dom";

export default function GameList() {
  const [games, setGames] = useState([]);
  const [loading, setLoading] = useState(true);
  const nav = useNavigate();

  useEffect(() => { fetchGames(); }, []);

  async function fetchGames() {
    setLoading(true);
    try {
      const res = await api.get("/games");
      setGames(res.data?.data || []);
    } catch (err) {
      alert("Error fetching games: " + (err.message || err));
    } finally {
      setLoading(false);
    }
  }

  async function handleDelete(id) {
    if (!window.confirm("Delete this game?")) return;
    try {
      await api.delete(`/games/${id}`);
      fetchGames();
    } catch (err) {
      alert("Delete failed: " + (err.message || err));
    }
  }

  return (
    <div style={{
      fontFamily: "Segoe UI, sans-serif",
      backgroundColor: "#f4f6f8",
      minHeight: "100vh",
      padding: 30
    }}>
      <h2 style={{
        color: "#0d47a1",
        marginBottom: 20
      }}>
        🎮 Game Management
      </h2>

      <div style={{ marginBottom: 20 }}>
        <Link
          to="/games/add"
          style={{
            backgroundColor: "#43a047",
            color: "white",
            padding: "10px 16px",
            borderRadius: 6,
            textDecoration: "none",
            fontWeight: "bold",
            boxShadow: "0 2px 5px rgba(0,0,0,0.1)"
          }}
        >
          + Add Game
        </Link>
      </div>

      {loading ? (
        <p style={{ color: "#666" }}>Loading games...</p>
      ) : (
        <div style={{ overflowX: "auto" }}>
          <table
            width="100%"
            cellPadding="10"
            style={{
              borderCollapse: "collapse",
              backgroundColor: "#fff",
              borderRadius: 8,
              boxShadow: "0 4px 8px rgba(0,0,0,0.05)",
              overflow: "hidden"
            }}
          >
            <thead style={{
              backgroundColor: "#1565c0",
              color: "white",
              textAlign: "left"
            }}>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Cost/Hour</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {games.length === 0 ? (
                <tr>
                  <td colSpan="5" style={{
                    textAlign: "center",
                    padding: "16px",
                    color: "#999"
                  }}>
                    No games found
                  </td>
                </tr>
              ) : (
                games.map((g, index) => (
                  <tr key={g.gameId} style={{
                    backgroundColor: index % 2 === 0 ? "#f9f9f9" : "#ffffff",
                    borderBottom: "1px solid #e0e0e0"
                  }}>
                    <td>{g.gameId}</td>
                    <td>{g.gameName}</td>
                    <td>${g.costPerHour.toFixed(2)}</td>
                    <td style={{
                      color: g.status === "Available" ? "#2e7d32" : "#c62828",
                      fontWeight: "bold"
                    }}>
                      {g.status}
                    </td>
                    <td>
                      <button
                        onClick={() => nav(`/games/edit/${g.gameId}`)}
                        style={{
                          backgroundColor: "#0288d1",
                          color: "white",
                          border: "none",
                          padding: "6px 12px",
                          borderRadius: 4,
                          cursor: "pointer",
                          marginRight: 8,
                          transition: "background-color 0.2s"
                        }}
                        onMouseEnter={e => e.target.style.backgroundColor = "#0277bd"}
                        onMouseLeave={e => e.target.style.backgroundColor = "#0288d1"}
                      >
                        Edit
                      </button>
                      <button
                        onClick={() => handleDelete(g.gameId)}
                        style={{
                          backgroundColor: "#e53935",
                          color: "white",
                          border: "none",
                          padding: "6px 12px",
                          borderRadius: 4,
                          cursor: "pointer",
                          transition: "background-color 0.2s"
                        }}
                        onMouseEnter={e => e.target.style.backgroundColor = "#c62828"}
                        onMouseLeave={e => e.target.style.backgroundColor = "#e53935"}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
