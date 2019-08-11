package dao.Impl;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Login;

public class LogingImpl extends Conexion {

    public Login starEdition(String user, String contr) throws Exception {
        Conexion();
        ResultSet rs;
        Login usuario = null;
        try {
            String sql = "select IDPER, NOMPER, APEPER, DNIPER, SEXPER, TIPPER from PERSONA WHERE USUPER LIKE ? and PASSPER LIKE ? and  ESTPER LIKE 'A'";
            PreparedStatement ps = this.getConectar().prepareCall(sql);
            ps.setString(1, user);
            ps.setString(2, contr);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Login();
                usuario.setIDPER(rs.getInt("IDPER"));
                usuario.setNOMPER(rs.getString("NOMPER"));
                usuario.setAPEPER(rs.getString("APEPER"));
                usuario.setDNIPER(rs.getString("DNIPER"));
                usuario.setSEXPER(rs.getString("SEXPER"));
                usuario.setTIPPER(rs.getString("TIPPER"));
                usuario.setUSEPER(user);
                usuario.setPASSPER(contr);
            }
            return usuario;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrrar();
        }
    }

}
