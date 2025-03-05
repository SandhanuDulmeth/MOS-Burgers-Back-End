package edu.icet.repository.custom.impl;

import edu.icet.Model.Admin;
import edu.icet.entity.AdminEntity;
import edu.icet.repository.custom.AdminRepository;
import edu.icet.util.CrudUtil;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Override
    public ArrayList<AdminEntity> gettAll() {
        ArrayList<AdminEntity> adminEntities = new ArrayList<>();
        try {
            // Execute query to retrieve all admin records
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM ADMIN;");
            while (resultSet.next()) {
                AdminEntity adminEntity = new AdminEntity();
                adminEntity.setId(String.valueOf(resultSet.getInt("ID")));
                adminEntity.setEmail(resultSet.getString("EMAIL"));
                adminEntity.setPassword(resultSet.getString("PASSWORD"));
                // Add mapping for additional columns if needed
                adminEntities.add(adminEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving admins", e);
        }
        return adminEntities;
    }

    @Override
    public String getPassword(String email) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT PASSWORD FROM ADMIN WHERE EMAIL = ?;", email);
            if (resultSet.next()) {
                return resultSet.getString("PASSWORD");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching password for email: " + email, e);
        }
    }

    @Override
    public Admin findByEmail(String email) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM ADMIN WHERE EMAIL = ?;", email);
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(String.valueOf(resultSet.getInt("ID")));
                admin.setEmail(resultSet.getString("EMAIL"));
                admin.setPassword(resultSet.getString("PASSWORD"));
                // Set additional fields if necessary
                return admin;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding admin by email: " + email, e);
        }
    }

    @Override
    public void save(Admin admin) {
        try {
            // Update existing admin's password
            CrudUtil.execute("UPDATE ADMIN SET PASSWORD = ? WHERE EMAIL = ?;",
                    admin.getPassword(), admin.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException("Error updating password for email: " + admin.getEmail(), e);
        }
    }
}
