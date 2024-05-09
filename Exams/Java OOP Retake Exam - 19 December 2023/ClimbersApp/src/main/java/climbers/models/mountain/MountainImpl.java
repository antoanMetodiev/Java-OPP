package climbers.models.mountain;

import climbers.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;

public class MountainImpl implements Mountain {
    private String name;
    private Collection<String> peaksList;

    public MountainImpl(String name) {
        this.setName(name);
        this.peaksList = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.MOUNTAIN_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getPeaksList() {
        return this.peaksList;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
