import React from "react";
import { Routes, Route, Link, useNavigate } from "react-router-dom";

import MemberList from "./components/MemberList";
import MemberForm from "./components/MemberForm";
import GameList from "./components/GameList";
import GameForm from "./components/GameForm";
import RechargeForm from "./components/RechargeForm";
import TransactionList from "./components/TransactionList";
import DailyCollection from "./components/DailyCollection";

// ✅ Navigation bar with styled buttons
function Nav() {
  const buttonStyle = {
    backgroundColor: "#20201ed3",
    color: "white",
    border: "none",
    padding: "10px 18px",
    borderRadius: "8px",
    cursor: "pointer",
    marginRight: "10px",
    fontWeight: "600",
    textDecoration: "none",
    fontFamily: "Inter, sans-serif",
    transition: "background 0.3s, transform 0.2s",
  };

  const navStyle = {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    gap: "12px",
    padding: "16px",
    borderBottom: "2px solid rgba(29, 28, 32, 0.69)",
    backgroundColor: "#bf0feb49",
  };

  const hoverStyle = {
    backgroundColor: "#5148e5",
  };

  return (
    <nav style={navStyle}>
      {[
        { to: "/", label: "Home" },
        { to: "/members", label: "Members" },
        { to: "/games", label: "Games" },
        { to: "/recharge", label: "Recharge" },
        { to: "/transactions", label: "Transactions" },
        { to: "/daily-collections", label: "Daily Collections" },
      ].map((link) => (
        <Link
          key={link.to}
          to={link.to}
          style={buttonStyle}
          onMouseOver={(e) =>
            (e.target.style.backgroundColor = hoverStyle.backgroundColor)
          }
          onMouseOut={(e) =>
            (e.target.style.backgroundColor = buttonStyle.backgroundColor)
          }
        >
          {link.label}
        </Link>
      ))}
    </nav>
  );
}

// ✅ Redesigned Home Page
function Home() {
  const navigate = useNavigate();

  const cards = [
    {
      title: "👥 Manage Members",
      desc: "Add, edit, and view all club members easily.",
      path: "/members",
    },
    {
      title: "🎲 Game Records",
      desc: "Track and manage games efficiently.",
      path: "/games",
    },
    {
      title: "💰 Recharge & Transactions",
      desc: "View recharges and transaction histories.",
      path: "/transactions",
    },
    {
      title: "📅 Daily Collections",
      desc: "Monitor daily revenue and performance.",
      path: "/daily-collections",
    },
  ];

  return (
    <div
      style={{
        minHeight: "90vh",
        textAlign: "center",
        padding: "60px 20px",
        background: "linear-gradient(135deg, #7430b4af, #928DAB)",
        fontFamily: "Inter, sans-serif",
        color: "white",
      }}
    >
      <h1
        style={{
          fontSize: "2.8rem",
          fontWeight: "700",
          letterSpacing: "0.5px",
          marginBottom: "12px",
        }}
      >
        🎮 Gaming Club Admin Panel
      </h1>
      <p
        style={{
          fontSize: "1.15rem",
          color: "rgba(255,255,255,0.85)",
          maxWidth: "650px",
          margin: "0 auto 40px auto",
          lineHeight: "1.6",
        }}
      >
        Manage your members, games, and transactions in a modern, easy-to-use
        admin dashboard.
      </p>

      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "center",
          gap: "25px",
        }}
      >
        {cards.map((card, i) => (
          <div
            key={i}
            style={{
              background:
                "rgba(255,255,255,0.12)",
              backdropFilter: "blur(10px)",
              borderRadius: "14px",
              boxShadow: "0 8px 20px rgba(0,0,0,0.3)",
              padding: "25px 20px",
              width: "250px",
              cursor: "pointer",
              transition: "transform 0.25s ease, box-shadow 0.25s ease",
            }}
            onMouseOver={(e) => {
              e.currentTarget.style.transform = "translateY(-8px)";
              e.currentTarget.style.boxShadow =
                "0 12px 25px rgba(0,0,0,0.5)";
            }}
            onMouseOut={(e) => {
              e.currentTarget.style.transform = "translateY(0)";
              e.currentTarget.style.boxShadow =
                "0 8px 20px rgba(0,0,0,0.3)";
            }}
            onClick={() => navigate(card.path)}
          >
            <h3
              style={{
                color: "#FFD700",
                fontWeight: "600",
                marginBottom: "10px",
              }}
            >
              {card.title}
            </h3>
            <p
              style={{
                color: "rgba(255,255,255,0.9)",
                fontSize: "0.95rem",
              }}
            >
              {card.desc}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}

// ✅ Main App component
export default function App() {
  return (
    <div
      style={{
        fontFamily: "Inter, sans-serif",
        maxWidth: 1200,
        margin: "0 auto",
      }}
    >
      <Nav />
      <div style={{ padding: 16 }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/members" element={<MemberList />} />
          <Route path="/members/add" element={<MemberForm />} />
          <Route path="/members/edit/:id" element={<MemberForm />} />
          <Route path="/games" element={<GameList />} />
          <Route path="/games/add" element={<GameForm />} />
          <Route path="/games/edit/:id" element={<GameForm />} />
          <Route path="/recharge" element={<RechargeForm />} />
          <Route path="/transactions" element={<TransactionList />} />
          <Route path="/daily-collections" element={<DailyCollection />} />
        </Routes>
      </div>
    </div>
  );
}