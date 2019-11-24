package school.team.musictuner;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static school.team.musictuner.ExampleInstrumentedTest.AUDIO_FILE;

public class AdvancedControllerTest {
    public static final String SAVE_FILE = "music_tuner_test";
    private class Record implements AdvancedDisplay {
        public final List list = new ArrayList();
        private boolean continueInput = false;
        @Override
        public void showStatus(String status) {
            if (continueInput) list.add(status);
        }

        @Override
        public void runOnUIThread(Runnable runnable) {
            runnable.run();
        }

        @Override
        public void displaySections(List<PlayedSection> sections) {
            if (continueInput) list.add(sections);
        }
        public void finish() {
            continueInput=false;
        }
        public void start() {
            continueInput=true;
        }
    }
    @Test
    public void testAdvancedControllerSaveLoad() {
        Record record1 = new Record();
        Record record2 = new Record();
        Converter converter = new Converter();
        converter.setSettings(new Settings());
        converter.setTuner(new Tuner());
        AdvancedController controller1 = new AdvancedController(converter);
        controller1.setDisplay(record1);
        controller1.read(AUDIO_FILE);
        controller1.save(SAVE_FILE);
        record1.start();
        doSteps1(controller1);
        record1.finish();
        controller1.destroy();
        AdvancedController controller2 = new AdvancedController(converter);
        controller2.setDisplay(record2);
        controller2.load(SAVE_FILE);
        record2.start();
        doSteps1(controller2);
        record2.finish();
        controller2.destroy();
        assertEquals(record1.list,record2.list);
    }
    private void doSteps1(AdvancedController controller) {
        controller.divide(0,0.7);

    }
}
