import java.util.*;
class ComparatorValues implements Comparator<Base> {
	public int compare(Base first, Base second) {
		if(first.getFrequency() != second.getFrequency()) {
            return (first.getFrequency() - second.getFrequency());
        }
		return 0;
	}
}