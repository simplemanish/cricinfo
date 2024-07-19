package com.ms.player.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.player.form.PlayerForm;
import com.ms.player.model.Player;
import com.ms.player.model.Role;
import com.ms.player.service.PlayerService;
import com.ms.player.service.RoleService;

/**
 * PlayerControllerTest is JUnit Test cases to test PlayerController REST API.
 * related operations.
 * 
 * <p>
 * It tests REST end-points of creating, retrieving, updating and deleting
 * player resource.
 * </p>
 * 
 * Please see the {@link com.ms.player.web.PlayerControllerTest} class
 * 
 * @author 047929
 * @version 1.0.0
 */
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

	/**
	 * mockMvc
	 */
	private MockMvc mockMvc;

	/**
	 * Mock playerService.
	 */
	@MockBean
	private PlayerService playerService;

	/**
	 * Mock roleService.
	 */
	@MockBean
	RoleService roleService;

	/**
	 * Inject mocks player controller.
	 */
	@InjectMocks
	private PlayerController playerController;

	Player player1 = new Player(1L, "Rishabh", "Pant", getDate("1997-10-04"), "Indian",
			new HashSet<Role>(Set.of(new Role(1L), new Role(2L))));
	Player player2 = new Player(2L, "player2", "lastname2", getDate("1997-10-05"), "Australian",
			new HashSet<Role>(Set.of(new Role(2L))));
	Player player3 = new Player(3L, "player3", "lastname3", getDate("1997-10-06"), "English",
			new HashSet<Role>(Set.of(new Role(3L))));
	Player player4 = new Player(4L, "player4", "lastname4", getDate("1997-10-07"), "English",
			new HashSet<Role>(Set.of(new Role(1L))));
	Player playerDuplicate4 = new Player(4L, "player4", "lastname4", getDate("1997-10-07"), "English",
			new HashSet<Role>(Set.of(new Role(1L))));

	PlayerForm playerForm1 = new PlayerForm(1L, "Rishabh", "Pant", "1997-10-04", "Indian",
			new HashSet<Long>(Set.of(1L, 2L)));

	/**
	 * set up mockMvc.
	 */
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
	}

	/**
	 * Test get player REST end-point for success.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPlayerDetails_success() throws Exception {
		when(playerService.getPlayerById(1L)).thenReturn(player1);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/player/1").accept(MediaType.APPLICATION_JSON)).andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		PlayerForm result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				PlayerForm.class);
		assertEquals(playerForm1, result);
	}

	/**
	 * Test get player REST end-point for player not found.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPlayerDetails_notFound() throws Exception {
		when(playerService.getPlayerById(1L)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/player/5").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());

		JSONObject resp = new JSONObject();
		resp.put("error", "Player not found");
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(resp.toString(), result);
	}

	/**
	 * Test get player REST end-point for bad request.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPlayerDetails_400() throws Exception {
		when(playerService.getPlayerById(1L)).thenReturn(player1);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/player/sdf").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
	}

	private Date getDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Calendar.getInstance().getTime();
	}

}
