package ru.drdrapp.webapp.testdata;

public class TestThread {

    public static void main(String[] args) {
        Address address = new Address();
        Thread tAddress = new Thread(address);
        Job job = new Job();
        Thread tJob = new Thread(job);
        tJob.start();
        tAddress.start();
        Person person = new Person("Hank", address, job);
        System.out.println(person);
    }

    static class Address implements Runnable {
        private String address;

        public String getAddress() {
            return address;
        }

        public Address() {
        }

        @Override
        public void run() {
            this.address = "LA";
        }
    }

    static class Job implements Runnable {
        private String job;

        public String getJob() {
            return job;
        }

        public Job() {
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.job = "Software Developer";
        }
    }

    static class Person {
        private String name;

        @Override
        public String toString() {
            return "Person [name=" + name + ", job=" + job.getJob() + ", address=" + address.getAddress() + "]";
        }

        private Job job;
        private Address address;

        public Person(String name, Address address, Job job) {
            this.name = name;
            this.address = address;
            this.job = job;
        }
    }

}