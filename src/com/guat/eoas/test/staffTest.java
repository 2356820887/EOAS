package com.guat.eoas.test;

import com.guat.eoas.pojo.Staff;
import com.guat.eoas.service.StaffService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class staffTest {

    private final StaffService service = new StaffService();

    @Test
    public void staffSelectTest() {
        Integer id = 1;
        String name = "C";
        String email = "outlook";
        String role = "员工";
        String status = "激活";
        List<Staff> staff = service.getStaff(null, null, null, role, status);
        for (Staff staff1 : staff) {
            System.out.println(staff1);
        }
    }

    @Test
    public void staffNameTest() {
        Integer id = 1;
        List<String> staffNames = service.getStaffNames(null);
        System.out.println(staffNames);
    }

    @Test
    public void staffAddTest() {
        Staff staff = new Staff();
        staff.setName("asda");
        staff.setEmail("email");
        staff.setGender("男");
        staff.setDepartment("dasdasdas");
        staff.setPosition("position");
        staff.setPhone("asdasdasd");
        Integer i = service.addStaff(staff);
        System.out.println(i);
    }
}
