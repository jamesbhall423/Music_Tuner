package school.team.musictuner;

import java.util.List;

public interface AdvancedDisplay {

    void showStatus(String status);

    void runOnUIThread(Runnable runnable);

    void displaySections(List<PlayedSection> sections);

}
