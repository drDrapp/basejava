/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    int countResume = 0;

    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    void save(Resume r) {
        if (storage.length == countResume) {
            return;
        }
        storage[countResume] = r;
        countResume++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[countResume - 1];
                storage[countResume - 1] = null;
                countResume--;
                break;
            }
        }
    }

    Resume[] getAll() {
        Resume[] newStorage = new Resume[countResume];
        System.arraycopy(storage, 0, newStorage, 0, countResume);
        return newStorage;
    }

    int size() {
        return countResume;
    }
}
