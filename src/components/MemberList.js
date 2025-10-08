import React, { useEffect, useState } from "react";
import api from "../api";
import { Link, useNavigate } from "react-router-dom";

export default function MemberList() {
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const nav = useNavigate();

  useEffect(() => { fetchMembers(); }, []);

  async function fetchMembers() {
    setLoading(true);
    try {
      const res = await api.get("/members");
      setMembers(res.data?.data || []);
    } catch (err) {
      alert("Error fetching members: " + (err.message || err));
    } finally {
      setLoading(false);
    }
  }

  async function handleDelete(id) {
    if (!window.confirm("Delete this member?")) return;
    try {
      await api.delete(`/members/${id}`);
      fetchMembers();
    } catch (err) {
      alert("Delete failed: " + (err.message || err));
    }
  }

  return (
    <div style={{ fontFamily: "Segoe UI, sans-serif", backgroundColor: "#f4f6f8", minHeight: "100vh", padding: "30px" }}>
      <h2 style={{ color: "#0d47a1", marginBottom: 20 }}>👥 Member Management</h2>

      {/* Add Member button-style link */}
      <div style={{ marginBottom: 20 }}>
        <Link
          to="/members/add"
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
          + Add Member
        </Link>
      </div>

      {loading ? (
        <p style={{ color: "#666" }}>Loading members...</p>
      ) : (
        <div style={{ overflowX: "auto" }}>
          <table
            width="100%"
            cellPadding="10"
            style={{
              borderCollapse: "collapse",
              backgroundColor: "#ffffff",
              borderRadius: 8,
              boxShadow: "0 4px 8px rgba(0,0,0,0.05)",
              overflow: "hidden"
            }}
          >
            <thead style={{ backgroundColor: "#1565c0", color: "white", textAlign: "left" }}>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Balance</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {members.length === 0 ? (
                <tr>
                  <td colSpan="7" style={{ textAlign: "center", padding: "16px", color: "#999" }}>
                    No members found
                  </td>
                </tr>
              ) : (
                members.map(m => (
                  <tr key={m.memberId} style={{ borderBottom: "1px solid #eee" }}>
                    <td>{m.memberId}</td>
                    <td>{m.name}</td>
                    <td>{m.email}</td>
                    <td>{m.phone}</td>
                    <td>${m.balance?.toFixed(2) ?? "-"}</td>
                    <td style={{
                      color: m.status === "Active" ? "#2e7d32" : "#c62828",
                      fontWeight: "bold"
                    }}>
                      {m.status}
                    </td>
                    <td>
                      <button
                        onClick={() => nav(`/members/edit/${m.memberId}`)}
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
                        onClick={() => handleDelete(m.memberId)}
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
