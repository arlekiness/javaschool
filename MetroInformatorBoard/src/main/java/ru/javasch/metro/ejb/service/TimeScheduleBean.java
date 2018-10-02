package ru.javasch.metro.ejb.service;


public class TimeScheduleBean {
    private DataManager dataManager = DataManager.getInstance();
    private Listener listener = new Listener();
    private Loader loader = Loader.getInstance();

    @Getter
    @Setter
    private List<TimeSchedule> schedulesDeparture;

    @Getter
    @Setter
    private List<TimeSchedule> schedulesArrival;

    @Getter
    private List<String> stations = loader.getStations();

    @Getter
    private String selectedItem = "Saint Petersburg";

    @Getter
    private String lastChangesInfo = "No changes ... ";

    public void update() {
        if (dataManager.getStatusChanges()) {
            lastChangesInfo = dataManager.getLastInfoChanges();
            log.info("update @schedule ... ");
            dataManager.resetStatusChanges();
            /*
            Update schedules for current station if it was updated in real time
             */
            if (dataManager.checkSelectedItem(selectedItem))
                updateSchedules(selectedItem);
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        updateSchedules(selectedItem);
    }

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        listener.start();
        updateSchedules(selectedItem);
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        listener.stop();
    }

    private void updateSchedules(String selectedItem) {
        schedulesDeparture = dataManager.loadScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.loadScheduleArrival(selectedItem);
    }
}
