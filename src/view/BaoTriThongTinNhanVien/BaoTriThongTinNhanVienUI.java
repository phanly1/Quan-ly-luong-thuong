package view.BaoTriThongTinNhanVien;

import controller.BaoTriThongTinNhanVienController;
import model.HeSoLuong;
import model.NhanVien;
import model.NhanVienDTO;
import model.PhongBan;
import view.DangKy.TaoTaiKhoanBoiAdminUI;
import view.TrangChu.TrangChuAdmin.TrangChuAdminUI;
import view.TuyChonUI;
import util.IOptionEvent;
import util.IUpdateTableEvent;
import view.QuanLyThongTinCaNhan.QuanLyThongTinCaNhanUI;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import util.ISendDataToAnotherGUI;

public class BaoTriThongTinNhanVienUI extends javax.swing.JFrame {
    private final BaoTriThongTinNhanVienController controller;
    private ArrayList<PhongBan> phongBans;
    private ArrayList<HeSoLuong> heSoLuongs;
    private final TaoTaiKhoanBoiAdminUI taoTaiKhoanBoiAdminUI;

    private HashMap<String, Object> data;

    public BaoTriThongTinNhanVienUI(HashMap<String, Object> data) throws SQLException {
        this.data = data;
        initComponents();
        setLocationRelativeTo(null);
        controller = new BaoTriThongTinNhanVienController();
        taoTaiKhoanBoiAdminUI = new TaoTaiKhoanBoiAdminUI(data);
        taoTaiKhoanBoiAdminUI.setPutDataInterface(new ISendDataToAnotherGUI() {
            @Override
            public void onGetData(HashMap<String, Object> getObjectData) {
                String taiKhoan = (String) getObjectData.get("TaiKhoan");
                int quyen = (Integer) getObjectData.get("Quyen");
                txtTenTaiKhoan.setText(taiKhoan);
                cboChucVu.setSelectedIndex(quyen);
            }
        });
        FirstTimeDataCall();
        controller.setUpdateTableEvent(new IUpdateTableEvent() {
            @Override
            public void onUpdateDataOnTableEvent() {
                try {
                    UpdateTable();
                } catch (SQLException ex) {
                    Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void FirstTimeDataCall() throws SQLException {
        phongBans = controller.onWriteComboBoxPhongBan();
        cboPhongBan.removeAllItems();

        for (PhongBan phongBan : phongBans) {
            cboPhongBan.addItem(phongBan.toString());
        }

        heSoLuongs = controller.onWriteComboBoxHeSoLuong();
        cboHeSoLuong.removeAllItems();

        for (HeSoLuong heSoLuong : heSoLuongs) {
            cboHeSoLuong.addItem(heSoLuong.toString());
        }

        cboPhongBan.setSelectedIndex(0);
        cboHeSoLuong.setSelectedIndex(0);

        UpdateTable();
    }

    private void UpdateTable() throws SQLException {
        ArrayList<NhanVienDTO> nhanVienDTOs = controller.onQueryAllTableNhanVien();

        DefaultTableModel defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();

        while (defaultTableModel.getRowCount() > 0) {
            defaultTableModel.removeRow(0);
        }

        for (NhanVienDTO nhanVienDTO : nhanVienDTOs) {
            defaultTableModel.addRow(nhanVienDTO.toObjectArrayData());
        }
    }

    private boolean validateData() {
        
        String message = "";
        int numErr = 0;
        try {
            if (txtDiaChi.getText().trim().equalsIgnoreCase("")) {
                message += "Địa Chỉ không được để trống!!\n";
                numErr += 1;
            }

            if (txtNgaySinh.getDate().toString().trim().equalsIgnoreCase("")) {
                message += "Ngày Sinh không được để trống!!\n";
                numErr += 1;
            }

            if (txtTenNhanVien.getText().trim().equalsIgnoreCase("")) {
                message += "Tên Nhân Viên không được để trống!!\n";
                numErr += 1;
            }

            if (txtTenTaiKhoan.getText().trim().equalsIgnoreCase("")) {
                message += "Tên Tài Khoản không được để trống!!\n";
                numErr += 1;
            }

            if (numErr > 0) {
                throw new Exception(message);
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getContentPane(), message);
        }
        return false;
    }

    private void clearAllTextBox() {
        txtDiaChi.setText("");
        txtMaNhanVien.setText("");
        txtNgaySinh.setDate(null);
        txtTenNhanVien.setText("");
        txtTenTaiKhoan.setText("");
        cboChucVu.setSelectedIndex(0);
        cboHeSoLuong.setSelectedIndex(0);
        cboPhongBan.setSelectedIndex(0);
        cboTrinhDo.setSelectedIndex(0);
        rdNam.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdGroupGioiTinh = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboChucVu = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboTrinhDo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        btnThemNhanVien = new javax.swing.JButton();
        btnSuaNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        btnXoaThongTin = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenTaiKhoan = new javax.swing.JTextField();
        cboHeSoLuong = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboPhongBan = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bảo Trì Thông Tin Nhân Viên");
        setResizable(false);

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+11));
        jLabel1.setText("Bảo trì thông tin Nhân Viên");

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getSize()+3f));
        jLabel2.setText("Mã Nhân Viên: ");

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setFont(txtMaNhanVien.getFont().deriveFont(txtMaNhanVien.getFont().getSize()+3f));

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getSize()+3f));
        jLabel3.setText("Tên Nhân Viên: ");

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getSize()+3f));
        jLabel4.setText("Giới Tính");

        txtTenNhanVien.setFont(txtTenNhanVien.getFont().deriveFont(txtTenNhanVien.getFont().getSize()+3f));

        rdGroupGioiTinh.add(rdNam);
        rdNam.setFont(rdNam.getFont().deriveFont(rdNam.getFont().getSize()+3f));
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        rdGroupGioiTinh.add(rdNu);
        rdNu.setFont(rdNu.getFont().deriveFont(rdNu.getFont().getSize()+3f));
        rdNu.setText("Nữ");

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getSize()+3f));
        jLabel5.setText("Ngày Sinh:");

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getSize()+3f));
        jLabel6.setText("Địa Chỉ:");

        txtDiaChi.setFont(txtDiaChi.getFont().deriveFont(txtDiaChi.getFont().getSize()+3f));

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getSize()+3f));
        jLabel7.setText("Chức Vụ:");

        cboChucVu.setFont(cboChucVu.getFont().deriveFont(cboChucVu.getFont().getSize()+3f));
        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Trị Viên", "Giảng Viên", "Kế Toán", "Nhân Sự", "Giám Đốc" }));

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getSize()+3f));
        jLabel8.setText("Trình Độ:");

        cboTrinhDo.setFont(cboTrinhDo.getFont().deriveFont(cboTrinhDo.getFont().getSize()+3f));
        cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đại Học", "Cao Đẳng", "Thạc Sĩ", "Tiến Sĩ", "Phó Giáo Sư", "Giáo Sư" }));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Chức Vụ", "Trình Độ", "Tên Tài Khoản", "Hệ Số Lương", "Phòng Ban"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        btnThemNhanVien.setFont(btnThemNhanVien.getFont().deriveFont(btnThemNhanVien.getFont().getSize()+3f));
        btnThemNhanVien.setText("Thêm Nhân Viên");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnSuaNhanVien.setFont(btnSuaNhanVien.getFont().deriveFont(btnSuaNhanVien.getFont().getSize()+3f));
        btnSuaNhanVien.setText("Sửa Nhân Viên");
        btnSuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setFont(btnXoaNhanVien.getFont().deriveFont(btnXoaNhanVien.getFont().getSize()+3f));
        btnXoaNhanVien.setText("Xóa Nhân Viên");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        btnXoaThongTin.setFont(btnXoaThongTin.getFont().deriveFont(btnXoaThongTin.getFont().getSize()+3f));
        btnXoaThongTin.setText("Xóa Thông Tin");
        btnXoaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThongTinActionPerformed(evt);
            }
        });

        jLabel9.setFont(jLabel9.getFont().deriveFont(jLabel9.getFont().getSize()+3f));
        jLabel9.setText("Tên Tài Khoản: ");

        jLabel10.setFont(jLabel10.getFont().deriveFont(jLabel10.getFont().getSize()+3f));
        jLabel10.setText("Hệ Số Lương: ");

        txtTenTaiKhoan.setFont(txtTenTaiKhoan.getFont().deriveFont(txtTenTaiKhoan.getFont().getSize()+3f));

        cboHeSoLuong.setFont(cboHeSoLuong.getFont().deriveFont(cboHeSoLuong.getFont().getSize()+3f));

        jLabel11.setFont(jLabel11.getFont().deriveFont(jLabel11.getFont().getSize()+3f));
        jLabel11.setText("Phòng Ban: ");

        cboPhongBan.setFont(cboPhongBan.getFont().deriveFont(cboPhongBan.getFont().getSize()+3f));

        back.setText("Quay lại");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        txtNgaySinh.setName("txtNgaySinh"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(292, Short.MAX_VALUE)
                .addComponent(btnThemNhanVien)
                .addGap(18, 18, 18)
                .addComponent(btnSuaNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaNhanVien)
                .addGap(18, 18, 18)
                .addComponent(btnXoaThongTin)
                .addGap(205, 205, 205))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(100, 100, 100))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(70, 70, 70)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboChucVu, 0, 197, Short.MAX_VALUE)
                                    .addComponent(txtMaNhanVien)
                                    .addComponent(txtTenNhanVien)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdNu))
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(back))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(cboHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel8)
                                                .addGap(51, 51, 51))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(60, 60, 60)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cboPhongBan, 0, 257, Short.MAX_VALUE)
                                            .addComponent(cboTrinhDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addComponent(jLabel2))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(back))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdNam)
                    .addComponent(rdNu)
                    .addComponent(jLabel11)
                    .addComponent(cboPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(15, 15, 15)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addComponent(cboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNhanVien)
                            .addComponent(btnSuaNhanVien)
                            .addComponent(btnXoaNhanVien)
                            .addComponent(btnXoaThongTin)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        TuyChonUI tuyChonUI = new TuyChonUI();
        tuyChonUI.setOnHandleOptionEvent(new IOptionEvent() {
            @Override
            public void onAcceptEvent() {
                if (validateData()) {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setGioiTinh(rdNam.isSelected() ? 0 : 1);
                    nhanVien.setDiaChi(txtDiaChi.getText().trim());
                    nhanVien.setTenNhanVien(txtTenNhanVien.getText().trim());
                    nhanVien.setChucVu(cboChucVu.getItemAt(cboChucVu.getSelectedIndex()));
                    nhanVien.setTrinhDo(cboTrinhDo.getItemAt(cboTrinhDo.getSelectedIndex()));
                    nhanVien.setNgaySinh(new java.sql.Date(txtNgaySinh.getDate().getTime()));
                    nhanVien.setMaPhongBan(phongBans.get(cboPhongBan.getSelectedIndex()).getMaPhong());
                    nhanVien.setMaHeSoLuong(heSoLuongs.get(cboHeSoLuong.getSelectedIndex()).getMaHeSoLuong());
                    try {
                        nhanVien.setMaTaiKhoan(controller.onFindAccountID(txtTenTaiKhoan.getText().trim()));
                    } catch (SQLException ex) {
                        Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        controller.addNhanVien(nhanVien);
                    } catch (SQLException ex) {
                        Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clearAllTextBox();
                    JOptionPane.showMessageDialog(getContentPane(), "Thêm Nhân Viên Mới Thành Công");
                }
            }

            @Override
            public void onCancelEvent() {
                clearAllTextBox();
            }
        });

        tuyChonUI.onCallGUI(getContentPane(), "Bạn có chắc chắn muốn thêm Nhân Viên này không ?", "Thông Báo");
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnSuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVienActionPerformed
        TuyChonUI tuyChonUI = new TuyChonUI();
        tuyChonUI.setOnHandleOptionEvent(new IOptionEvent() {
            @Override
            public void onAcceptEvent() {
                if (validateData()) {
                    if (txtMaNhanVien.getText().equals("")) {
                        JOptionPane.showMessageDialog(getContentPane(), "Bạn chưa chọn Nhân Viên cần xóa!!");
                        return;
                    }

                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setGioiTinh(rdNam.isSelected() ? 0 : 1);
                    nhanVien.setDiaChi(txtDiaChi.getText().trim());
                    nhanVien.setTenNhanVien(txtTenNhanVien.getText().trim());
                    nhanVien.setChucVu(cboChucVu.getItemAt(cboChucVu.getSelectedIndex()));
                    nhanVien.setTrinhDo(cboTrinhDo.getItemAt(cboTrinhDo.getSelectedIndex()));
                    nhanVien.setNgaySinh(new java.sql.Date(txtNgaySinh.getDate().getTime()));
                    nhanVien.setMaPhongBan(phongBans.get(cboPhongBan.getSelectedIndex()).getMaPhong());
                    nhanVien.setMaHeSoLuong(heSoLuongs.get(cboHeSoLuong.getSelectedIndex()).getMaHeSoLuong());
                    nhanVien.setMaNhanVien(Long.parseLong(txtMaNhanVien.getText()));

                    try {
                        nhanVien.setMaTaiKhoan(controller.onFindAccountID(txtTenTaiKhoan.getText().trim()));
                    } catch (SQLException ex) {
                        Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        controller.modifyNhanVienByID(nhanVien);
                    } catch (SQLException ex) {
                        Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clearAllTextBox();
                    JOptionPane.showMessageDialog(getContentPane(), "Sửa Nhân Viên Thành Công");
                }
            }

            @Override
            public void onCancelEvent() {
                clearAllTextBox();
            }
        });

        tuyChonUI.onCallGUI(getContentPane(), "Bạn có chắc chắn muốn sửa Nhân Viên này không ?", "Thông Báo");
    }//GEN-LAST:event_btnSuaNhanVienActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        TuyChonUI tuyChonUI = new TuyChonUI();
        tuyChonUI.setOnHandleOptionEvent(new IOptionEvent() {
            @Override
            public void onAcceptEvent() {
                if (validateData()) {
                    if (txtMaNhanVien.getText().equals("")) {
                        JOptionPane.showMessageDialog(getContentPane(), "Bạn chưa chọn Nhân Viên cần xóa!!");
                        return;
                    }

                    try {
                        controller.deleteNhanVienByID(Long.parseLong(txtMaNhanVien.getText().trim()));
                    } catch (SQLException ex) {
                        Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    clearAllTextBox();
                    JOptionPane.showMessageDialog(getContentPane(), "Xóa Nhân Viên thành công!!");
                }
            }

            @Override
            public void onCancelEvent() {
            }
        });
        tuyChonUI.onCallGUI(getContentPane(), "Bạn có muốn xóa Nhân Viên này?", "Thông Báo");
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void btnXoaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThongTinActionPerformed
        TuyChonUI tuyChonUI = new TuyChonUI();
        tuyChonUI.setOnHandleOptionEvent(new IOptionEvent() {
            @Override
            public void onAcceptEvent() {
                clearAllTextBox();
                JOptionPane.showMessageDialog(getContentPane(), "Xóa thông tin thành công!!");
            }

            @Override
            public void onCancelEvent() {
                clearAllTextBox();
            }
        });

        tuyChonUI.onCallGUI(getContentPane(), "Bạn có chắc chắn muốn xóa thông tin này không ?", "Thông Báo");
    }//GEN-LAST:event_btnXoaThongTinActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int rowIndex = tblNhanVien.getSelectedRow();
        TableModel model = tblNhanVien.getModel();

        String maNhanVien = tblNhanVien.getValueAt(rowIndex, 0).toString();
        String tenNhanVien = tblNhanVien.getValueAt(rowIndex, 1).toString();
        String gioiTinh = tblNhanVien.getValueAt(rowIndex, 2).toString();
        String ngaySinh = tblNhanVien.getValueAt(rowIndex, 3).toString();
        String diaChi = tblNhanVien.getValueAt(rowIndex, 4).toString();
        String chucVu = tblNhanVien.getValueAt(rowIndex, 5).toString();
        String trinhDo = tblNhanVien.getValueAt(rowIndex, 6).toString();
        String tenTaiKhoan = tblNhanVien.getValueAt(rowIndex, 7).toString();
        String heSoLuong = tblNhanVien.getValueAt(rowIndex, 8).toString();
        String phongBan = tblNhanVien.getValueAt(rowIndex, 9).toString();

        txtMaNhanVien.setText(maNhanVien);
        txtTenNhanVien.setText(tenNhanVien);

        if (gioiTinh.toLowerCase().trim().equals("nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
        try {
            txtNgaySinh.setDate(simpleDateFormat.parse(ngaySinh));
        } catch (ParseException ex) {
            Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtDiaChi.setText(diaChi);

        for (int i = 0; i < cboChucVu.getItemCount(); i++) {
            if (cboChucVu.getItemAt(i).equals(chucVu)) {
                cboChucVu.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < cboTrinhDo.getItemCount(); i++) {
            if (cboTrinhDo.getItemAt(i).equals(trinhDo)) {
                cboTrinhDo.setSelectedIndex(i);
                break;
            }
        }

        txtTenTaiKhoan.setText(tenTaiKhoan);

        for (int i = 0; i < cboHeSoLuong.getItemCount(); i++) {
            var temp1 = Float.parseFloat(cboHeSoLuong.getItemAt(i));
            var temp2 = Float.parseFloat(heSoLuong);

            if (temp1 == temp2) {
                cboHeSoLuong.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < cboPhongBan.getItemCount(); i++) {
            if (cboPhongBan.getItemAt(i).equals(phongBan)) {
                cboPhongBan.setSelectedIndex(i);
                break;
            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public void onStartGUI() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BaoTriThongTinNhanVienUI(data).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(BaoTriThongTinNhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton btnSuaNhanVien;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.JButton btnXoaThongTin;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboHeSoLuong;
    private javax.swing.JComboBox<String> cboPhongBan;
    private javax.swing.JComboBox<String> cboTrinhDo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup rdGroupGioiTinh;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNhanVien;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtTenNhanVien;
    public javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables

}
