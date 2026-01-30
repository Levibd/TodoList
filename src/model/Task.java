package model;


import java.time.LocalDateTime;


public class Task {

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    private String name;
    private String description;
    private Integer priority;
    private LocalDateTime endDate;
    private Category category;
    private Status status;
    private boolean hasAlarm;
    private int minutesBefore;


    private Task(TaskBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.endDate = builder.endDate;
        this.priority = builder.priority;
        this.category = builder.category;
        this.status = builder.status;
        this.hasAlarm = builder.hasAlarm;
        this.minutesBefore = builder.minutesBefore;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {return description;}

    public Integer getPriority() {
        return priority;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Category getCategory() {return category;}

    public Status getStatus() {return status;}

    public boolean hasAlarm() {return hasAlarm;}

    public int getMinutesBefore() {return minutesBefore;}

    public static class TaskBuilder {
        private String name;
        private String description;
        private Integer priority;
        private LocalDateTime endDate;
        private Category category;
        private Status status;
        private boolean hasAlarm;
        private int minutesBefore;

        public TaskBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public TaskBuilder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public TaskBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public TaskBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public TaskBuilder withAlarm(boolean hasAlarm, int minutesBefore) {
            this.hasAlarm = hasAlarm;
            this.minutesBefore = minutesBefore;
            return this;
        }

        public Task build() {

            if(priority != null && (priority < 1 || priority > 5)) {
                throw new IllegalArgumentException("Prioridade deve ser entre 1 e 5.");
            }
            return new Task(this);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "------------------------------------------------------\n" +
                "Nome: " + name + "\n" +
                "Descrição: " + description + "\n" +
                "Categoria: " + category + " | Prioridade: " + priority + "\n" +
                "Status: " + status + "\n" +
                "------------------------------------------------------";
    }
}
