/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.stereotype.Component;

/**
 *
 * @author tuvshuu
 */
@Component
public class JsonConverter {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T jsonToObject(String data, Class<T> clazz) throws IOException, NullPointerException {
        T t = mapper.readValue(data, clazz);
        return t;
    }
}
