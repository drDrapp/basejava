/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    int CountResume = 0;

    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < CountResume; i++) {
            storage[i] = null;
        }
        CountResume = 0;
    }

    void save(Resume r) {
        if (storage.length == CountResume) {
            return;
        }
        storage[CountResume] = r;
        CountResume++;
    }

    Resume get(String uuid) {
        int Index = 0;
        while (true) {
            if (Index == CountResume) {
                return null;
            }
            if (storage[Index].uuid.equals(uuid)) {
                return storage[Index];
            }
            Index++;
        }
    }

    void delete(String uuid) {
        int Index = 0;
        while (Index < CountResume) {
            if (storage[Index].uuid.equals(uuid)) {
                storage[Index] = storage[CountResume - 1];
                storage[CountResume - 1] = null;
                CountResume--;
            }
            Index++;
        }
    }

    Resume[] getAll() {
        Resume[] NewStorage = new Resume[CountResume];
        System.arraycopy(storage, 0, NewStorage, 0, CountResume);
        return NewStorage;
    }

    int size() {
        return CountResume;
    }
}
