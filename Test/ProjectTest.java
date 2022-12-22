import com.company.GUI.*;
import com.company.Model.DatabaseOperation;
import com.company.Model.History;
import com.company.Model.Item;
import com.company.Model.Student;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ProjectTest

{
    DatabaseOperation dao = new DatabaseOperation();

    @Test
    public void AddAndPullTest(){
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        assertEquals(student.getStudentPassword(), "1234");
        dao.deleteStudent(student.getStudentId());
    }
    @Test
    public void LoginTest(){
        dao.addStudent("sample", "sampleSur", "lin@ozu.edu.tr","1234");
        boolean isTrue = dao.checkForStudentLogin("lin@ozu.edu.tr","1234");
        assertTrue(isTrue);
        dao.deleteStudentByEmail("lin@ozu.edu.tr");

    }
    @Test
    public void GetStudentHistoryTest(){

        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        dao.addItem("y","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("y");
        dao.reserveItem(60, student.getStudentId(), item.getItemId());
        ArrayList<History> history = dao.getStudentHistory(student.getStudentId());
        assertEquals(history.get(history.size()-1).getItemID(),item.getItemId());
        dao.removeRes(60);
        dao.deleteItem(item.getItemId());
        dao.deleteStudent(student.getStudentId());

    }
    @Test
    public void AddItemTest(){
        dao.addItem("y","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("y");
        assertEquals(item.getLocation(), "y");
        dao.deleteItem(item.getItemId());
    }
    @Test
    public void ModifyItemTest(){
        dao.addItemById(66,"x","s. sample", 1999,120,true,"hh");
        dao.modifyItem(66, false);
        Item item = dao.findItemById(66);
        assertEquals(item.isAvailable(), false);
        dao.deleteItem(item.getItemId());
    }
    @Test
    public void DeleteItemTest(){
        dao.addItem("y","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("y");
        dao.deleteItem(item.getItemId());
        item = dao.findItemByLocation("y");
        assertNull(item);
    }

    @Test
    public void SearchByNameTest(){
        dao.addItem("ubuntu1","a", 1999,120,true,"hh");
        ArrayList<Item> items = dao.findItemByName("hh");
        Item item = items.get(items.size()-1);
        assertEquals(item.getLocation(), "ubuntu1");
        dao.deleteItem(item.getItemId());

    }
    @Test
    public void SearchByIdTest(){
        dao.addItemById(22,"uu","s. sample", 1999,120,true,"hh");
        Item item = dao.findItemById(22);
        assertEquals(item.getLocation(), "uu");
        dao.deleteItem(22);
    }
    @Test
    public void SearchByAuthorTest(){
        dao.addItem("aa","p", 1999,120,true,"hh");
        Item item = dao.findItemByAuthor("p").get(0);
        assertEquals(item.getLocation(), "aa");
        dao.deleteItem(item.getItemId());
    }

    @Test
    public void GetHistoryTest(){
        dao.reserveItem(55, 1,69);
        ArrayList<History> histories = dao.getAllHistory();
        assertEquals(histories.get(0).getHistoryID(), 11 );
        dao.removeRes(55);
    }

    @Test
    public void RemoveResTest(){
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        dao.addItem("gg","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("gg");

        dao.reserveItem(22, student.getStudentId(), item.getItemId());
        History history = dao.getHistoryById(22);
        dao.removeRes(22);
        dao.deleteItem(item.getItemId());
        dao.deleteStudent(student.getStudentId());

        history = dao.getHistoryById(22);
        assertNull(history);
    }
    @Test
    public void ReserveTest(){
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        dao.addItem("gg","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("gg");
        dao.reserveItemWithoutId(student.getStudentId(), item.getItemId());
        ArrayList<History> histories = dao.getAllHistory();
        History history = histories.get(histories.size()-1);
        assertEquals(history.getItemID(),item.getItemId() );

        dao.removeRes(history.getHistoryID());
        dao.deleteItem(item.getItemId());
        dao.deleteStudent(student.getStudentId());
    }
    @Test
    public void BlockStudentTest(){
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        dao.blockStudent(student.getStudentId());
        student = dao.pullStudent("sale@ozu.edu.tr");
        assertEquals(student.isBlocked(), false);
        dao.deleteStudent(student.getStudentId());
    }
    @Test
    public void UnBlockStudentTest(){
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        dao.unBlockStudent(student.getStudentId());
        student = dao.pullStudent("sale@ozu.edu.tr");
        assertEquals(student.isBlocked(), true);
        dao.deleteStudent(student.getStudentId());
    }
    @Test
    public void AdminPageTest(){
        AdminPage adminPage = new AdminPage();
        dao.addItem("gg","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("gg");
        dao.addStudent("sample", "sampleSur", "yy","1234");
        Student student = dao.pullStudent("yy");
        dao.reserveItem(1111, student.getStudentId(),item.getItemId());
        ArrayList<History> list = adminPage.getAllHistory();
        assertEquals(list.get(list.size()-1).getItemID(), item.getItemId() );

        dao.removeRes(1111);
        dao.deleteItem(item.getItemId());
        dao.deleteStudent(student.getStudentId());

    }
    @Test
    public void HomePageTest(){
        HomePage homePage = new HomePage();
        dao.addItem("uu","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("uu");
        ArrayList<Item> items = homePage.findItemByName(item.getName());
        assertEquals(items.get(items.size()-1).getItemId(), item.getItemId() );
        items = homePage.findItemByAuthor(item.getAuthor());
        assertEquals(items.get(items.size()-1).getItemId(), item.getItemId() );
        dao.deleteItem(item.getItemId());
    }
    @Test
    public void BookOperationsTest(){
        BookOperations bookOperations = new BookOperations();
        bookOperations.addItem("y","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("y");
        assertEquals(item.getLocation(), "y");

        bookOperations.modifyItem(item.getItemId(), false);
        item = dao.findItemById(item.getItemId());
        assertEquals(item.isAvailable(), false);

        bookOperations.deleteItem(item.getItemId());
        item = dao.findItemById(item.getItemId());
        assertNull(item);
    }
    @Test
    public void AllItemsTest(){

        dao.addItem("uu","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("uu");
        ArrayList<Item> items = dao.getAllItems();

        assertEquals(items.get(items.size()-1).getItemId(), item.getItemId() );
        dao.deleteItem(item.getItemId());
    }
    @Test
    public void LPanelTest(){
        LPanel lpanel = new LPanel();
        dao.addItem("uu","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("uu");
        ArrayList<Item> items = lpanel.getAllItems();

        assertEquals(items.get(items.size()-1).getItemId(), item.getItemId() );
        dao.deleteItem(item.getItemId());
    }

    @Test
    public void LoginPageTest(){
        JFrame frame = new JFrame();
        LoginPage login = new LoginPage(frame);
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        login.login(student.getStudentMail(), student.getStudentPassword());
        dao.deleteStudent(student.getStudentId());
    }
    @Test
    public void SignUpPageTest(){
        SignUpPage signUpPage= new SignUpPage(dao);

        signUpPage.setVisible(false);

        signUpPage.signUp("az", "sampleSur", "z","1234","1234");
        Student student = dao.pullStudent("az");
        assertEquals(student.getStudentPassword(),"1234");
        dao.deleteStudent(student.getStudentId());
    }
    @Test
    public void UserPageTest(){
        UserPage userPage = new UserPage();
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        dao.addItem("y","s. sample", 1999,120,true,"sample");
        Item item = dao.findItemByLocation("y");
        dao.reserveItem(60, student.getStudentId(), item.getItemId());
        ArrayList<History> history = userPage.getStudentHistory(student.getStudentId());
        assertEquals(history.get(history.size()-1).getItemID(),item.getItemId());
        dao.removeRes(60);
        dao.deleteItem(item.getItemId());
        dao.deleteStudent(student.getStudentId());

    }
    @Test
    public void blockPageTest(){
        BlockPage blockPage = new BlockPage();
        dao.addStudent("sample", "sampleSur", "sale@ozu.edu.tr","1234");
        Student student = dao.pullStudent("sale@ozu.edu.tr");
        blockPage.blockStudent(student.getStudentId());
        student = dao.pullStudent("sale@ozu.edu.tr");
        assertEquals(student.isBlocked(),false);
        blockPage.unBlockStudent(student.getStudentId());
        student = dao.pullStudent("sale@ozu.edu.tr");
        assertEquals(student.isBlocked(),true);

        dao.deleteStudent(student.getStudentId());

    }

}
