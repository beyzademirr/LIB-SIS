import com.company.Model.DatabaseOperation;
import com.company.Model.Item;
import com.company.Model.Student;
import org.junit.Test;

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
        System.out.println(student.getStudentId());
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
        dao.addItem("33","s. sample", 1999,120,true,"hh");
        Item item = dao.findItemByName("hh").get(0);
        assertEquals(item.getLocation(), "33");
        dao.deleteItem(item.getItemId());

    }
    @Test
    public void SearchByIdTest(){
        dao.addItemById(77,"tt","s. sample", 1999,120,true,"hh");
        Item item = dao.findItemById(77);
        assertEquals(item.getLocation(), "tt");
        dao.deleteItem(item.getItemId());
    }
    @Test
    public void SearchByAuthorTest(){
        dao.addItem("tt","s. sample", 1999,120,true,"hh");
        Item item = dao.findItemByAuthor("s. sample").get(0);
        assertEquals(item.getLocation(), "tt");
        dao.deleteItem(item.getItemId());
    }




}