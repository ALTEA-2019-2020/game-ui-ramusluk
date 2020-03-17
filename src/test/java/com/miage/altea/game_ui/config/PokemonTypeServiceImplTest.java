package com.miage.altea.game_ui.config;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PokemonTypeServiceImplTest {

    @Autowired
    PokemonTypeServiceImpl pokemonTypeService;

    @Mock
    RestTemplate restTemplate;

    @Value("${pokemonType.service.url}pokemon-types/25")
    String expectedUrl;

    @Autowired
    CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pokemonTypeService.setRestTemplate(restTemplate);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("Pikachu");
        when(restTemplate.getForObject(expectedUrl, PokemonType.class)).thenReturn(pikachu);
    }

    @Test
    void getPokemonType_shouldUseCache() {
        pokemonTypeService.getPokemon(25);

        // rest template should have been called once
        verify(restTemplate).getForObject(expectedUrl, PokemonType.class);

        pokemonTypeService.getPokemon(25);

        // rest template should not be called anymore because result is in cache !
        verifyNoMoreInteractions(restTemplate);

        // one result should be in cache !
        var cachedValue = cacheManager.getCache("pokemon-type").get(25).get();
        assertNotNull(cachedValue);
        assertEquals(PokemonType.class, cachedValue.getClass());
        assertEquals("Pikachu", ((PokemonType)cachedValue).getName());
    }
}