package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected int getIndexByUuid(String uuid) {
        var searchResume = new Resume(uuid, "DUMMY");
        return Arrays.binarySearch(storage, 0, countResume, searchResume, RESUME_COMPARATOR);
    }

    @Override
    protected void saveByIndex(Resume r, int index) {
        int insertionIndex = -index - 1;
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, countResume - insertionIndex);
        storage[insertionIndex] = r;
    }

    @Override
    protected void deleteByIndex(int index) {
        System.arraycopy(storage,index+1, storage, index, countResume - index - 1);
    }
}
