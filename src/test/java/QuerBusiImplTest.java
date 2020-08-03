import com.test.business.impl.QuerBusiImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuerBusiImplTest {

    @org.junit.jupiter.api.Test
    void queryList() {
        QuerBusiImpl querBusi = new QuerBusiImpl();
        List list = querBusi.queryList(3);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @org.junit.jupiter.api.Test
    void modifyListStatus() {
        QuerBusiImpl querBusi = new QuerBusiImpl();
        List list = querBusi.queryList(3);
        querBusi.modifyListStatus(list, "10D");

    }
}