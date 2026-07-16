-- TablePro SQL Export
-- Generated: 2026-07-16T02:26:03Z
-- Database Type: MySQL

DROP TABLE IF EXISTS `users` CASCADE;
DROP TABLE IF EXISTS `ledger_accounts` CASCADE;

-- --------------------------------------------------------
-- Table: ledger_accounts
-- --------------------------------------------------------

CREATE TABLE `ledger_accounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------
-- Table: users
-- --------------------------------------------------------

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `ledger_accounts` (`id`, `description`, `name`) VALUES
  (3, 'Revenue from new luxury vehicle sales', 'New Vehicle Sales'),
  (4, 'Revenue from used and certified pre-owned inventory', 'Pre-Owned & Certified Sales'),
  (5, 'Extended warranties, GAP insurance, financing income', 'Finance & Insurance (F&I)'),
  (6, 'Routine maintenance and mechanical repairs', 'Service & Repair'),
  (7, 'Parts department sales and OEM accessory installs', 'Parts & Accessories'),
  (8, 'Prepping trade-ins and pre-owned units for resale', 'Detailing & Reconditioning'),
  (9, 'Collision repair and bodywork', 'Body Shop & Collision'),
  (10, 'Digital ads, print, event sponsorships', 'Marketing & Advertising'),
  (11, 'Staff wages and sales commission payouts', 'Payroll & Commissions'),
  (12, 'Rent/mortgage, electricity, building maintenance', 'Facilities & Utilities'),
  (13, 'Interest on inventory financing from manufacturer/lender', 'Floor Plan Financing'),
  (14, 'Dealer bonding, liability coverage, state licensing', 'Insurance & Licensing'),
  (15, 'Manufacturer warranty claim reimbursements', 'Warranty & Recall Reimbursement'),
  (16, 'Loaner vehicles, valet pickup/delivery, VIP events', 'Client Concierge Services');


INSERT INTO `users` (`user_id`, `username`, `hashed_password`, `role`) VALUES
  (2, 'admin', '$2a$10$bor/MENpZl.XRNWw/LXL8.q2WZ9IVtghs4pUGPa2qg4tPQCCfDvyO', 'ADMIN');


