import com.dish.dgc.pong.model.QueueEntry;
import com.dish.dgc.pong.service.LinkedListQueueService;
import com.dish.dgc.pong.service.QueueService;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class LinkedListQueueServiceTest {

    private QueueService service;
    @Before
    public void setUp() throws Exception {
        service = new LinkedListQueueService();
    }

    @Test
    public void getQueue_returnsEmptyLinkedListIfEmpty() throws Exception {
        Queue<QueueEntry> actual = service.getQueue();

        assertThat(actual, equalTo(new LinkedList<QueueEntry>()));
    }

    @Test
    public void addToQueue_addsEntryToQueue() throws Exception {
        QueueEntry entry = new QueueEntry("name", "team");

        service.addToQueue(entry);

        Queue<QueueEntry> expected = new LinkedList<>();
        expected.add(entry);

        Queue<QueueEntry> actual = service.getQueue();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void getPosition() throws Exception {
        for(int i = 0; i < 5; i++) {
            service.addToQueue(new QueueEntry("name " + i, "team " + i));
        }

        assertThat(service.getPosition(new QueueEntry("name 0", "team 0")), equalTo(1));
        assertThat(service.getPosition(new QueueEntry("name 1", "team 1")), equalTo(2));
        assertThat(service.getPosition(new QueueEntry("name 2", "team 2")), equalTo(3));
        assertThat(service.getPosition(new QueueEntry("name 3", "team 3")), equalTo(4));
        assertThat(service.getPosition(new QueueEntry("name 4", "team 4")), equalTo(5));
    }

    @Test
    public void emptyQueue_emptysTheQueue() throws Exception {
        for(int i = 0; i < 5; i++) {
            service.addToQueue(new QueueEntry("name " + i, "team " + i));
        }

        service.emptyQueue();

        assertThat(service.getQueue().size(), equalTo(0));
    }
}