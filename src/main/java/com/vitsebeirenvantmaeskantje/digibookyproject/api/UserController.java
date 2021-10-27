package com.vitsebeirenvantmaeskantje.digibookyproject.api;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateAdminDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateLibrarianDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateMemberDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createMember(@RequestBody CreateMemberDto createMemberDto) {
        logger.info("Creating new member for email: " + createMemberDto.getMail());
        return userService.createNewMember(createMemberDto);
    }

    @PostMapping(path = "/registerAdmin", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createAdmin(@RequestBody CreateAdminDto createAdminDto) {
        // CODEREVIEW Improve URL, remove verb
        // TODO: (after security) make sure only admins can create new admins
        logger.info("Trying to create admin...");
        return userService.createNewAdmin(createAdminDto);
    }

    @PostMapping(path = "/registerLibrarian", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createLibrarian(@RequestBody CreateLibrarianDto createLibrarianDto) {
        // CODEREVIEW Improve URL, remove verb
        // TODO: (after security) make sure only admins can create new librarians
        logger.info("Trying to create librarian...");
        return userService.createNewLibrarian(createLibrarianDto);
    }

    //GETTERS
    @GetMapping(path = "/{id}/members", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllMembers(@PathVariable String id) {
        // CODEREVIEW: What is the `id` parameter?
        // FIXME: Rename to `adminId` (temporary)
        // FIXME: Remove {id} from path, turn from path variable into header with @RequestHeader (temporary)
        // TODO: Why temporary?? Until you add 'real' (spring) security
        logger.info("Gathering all members...");
        return userService.getAllMembers(id);
    }
}
