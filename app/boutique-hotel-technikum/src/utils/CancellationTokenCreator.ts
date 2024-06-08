class CancellationToken {
    private cancelledInternal = false;
    public get cancelled() { return this.cancelledInternal; }
    public cancel() {
        this.cancelledInternal = true;
    }
}

export default class CancellationTokenCreator {
    private token = new CancellationToken();

    public cancelOldAndGetNewToken() {
        this.token.cancel();
        this.token = new CancellationToken();
        return this.token;
    }
}