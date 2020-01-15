package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    Map<Long, TimeEntry> map;
    private long idSeq = 1L;

    public InMemoryTimeEntryRepository() {
        map = new HashMap<>();
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id = timeEntry.getId();
        if (id == 0) {
            timeEntry.setId(idSeq++);
        }
        map.put(timeEntry.getId(), timeEntry);
        return find(timeEntry.getId());
    }

    @Override
    public TimeEntry find(Long id) {
        return map.get(id);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        if(find(timeEntry.getId()) != null) {
            map.put(timeEntry.getId(), timeEntry);
        }
        return find(timeEntry.getId());
    }

    @Override
    public List<TimeEntry> list() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        map.remove(id);
      //  lastDeletedEntry = id;
    }
}
