package mapper;

import lombok.extern.slf4j.Slf4j;
import pojo.schema;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class tagMap {

    private Map<keyMap,entryRead> mappers;


    public tagMap(Map<keyMap, entryRead> mappers) {
        this.mappers = Collections.unmodifiableMap(mappers);
        public entryRead match(schema event){
            keyMap key = new keyMap(event.getf1(),event.getf2());
            return mappers.get(key);

        }

        public Map<keyMap,entryRead> getMappings(){return mappers;}
    }
}
