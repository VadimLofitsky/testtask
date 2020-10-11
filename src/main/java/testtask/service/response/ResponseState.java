package testtask.service.response;

public enum ResponseState {

    OK("ok"),
    ERROR("error");

    private String state;

    ResponseState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}