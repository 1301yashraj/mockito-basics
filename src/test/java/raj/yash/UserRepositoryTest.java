package raj.yash;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import raj.yash.model.User;
import raj.yash.repository.UserRespository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRespository userRepository;

    @Test
    @DisplayName("FindById")
    void findByID(){
        //Maria Anders
        Optional<User> result = userRepository.findById("ALFKI");
        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getFirstName());
    }

    @Test
    @DisplayName("FindAll")
    void testFindAll(){
        List<User> users = userRepository.findAll();
        assertEquals(91,users.size());
    }

    @Test
    @DisplayName("Query created from MethodName")
    void testOrder(){
        User user1 = userRepository.findFirstByOrderByFullNameAsc(); //Alejandra Camino
        Page<User> userPage = userRepository.findAll(PageRequest.of(1,3));
        assertAll(
           ()->assertEquals("Alejandra Camino",user1.getFullName()),
           ()-> assertEquals(3,userPage.getSize())
        );

//        assertEquals("AlejandraSCamino",user1.getFullName());
//         assertEquals(3,userPage.getSize());
    }


    @Test
    @DisplayName("Stream")
    void testStreamable(){
        try(Stream<User> result =
                userRepository.findByCompanyContaining("Romero y tomillo")
                        .stream().distinct()
                ){
            assertEquals(1,result.count());
        }
    }


    @Nested
    class Query{
        @Test
        @DisplayName("Custom Query Not Native")
        void testCustomNN(){
            assertEquals(3,userRepository.findAllByCity("London","Sales Representative"));
        }

        @Test
        @DisplayName("Custom Query Native")
        void testCustomN(){
            assertEquals(6,userRepository.findAllByCityNative("London"));
        }

        @Test
        @DisplayName("Query using Spring Expression Lang.")
        void testSpringQueryLang(){
            //Sales Agent ?>> 5
            List<Object[]> userList =  userRepository.findByAsArrayAndSort("Sales Agent", Sort.by("city"));
            List<Object[]> userList2 =  userRepository.findByAsArrayAndSort("Sales Agent", Sort.by("title"));

            assertAll(
                    ()-> assertEquals(7,userList.size()),
                    ()-> assertEquals("Catherine Dewey", userList.get(0)[0]),
                    ()-> assertEquals("59000",userList2.get(0)[1])
            );


        }

    }



}
