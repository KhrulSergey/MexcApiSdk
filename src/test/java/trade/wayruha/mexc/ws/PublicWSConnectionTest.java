package trade.wayruha.mexc.ws;

import lombok.SneakyThrows;
import okhttp3.Response;
import org.junit.Test;
import trade.wayruha.mexc.MexcConfig;
import trade.wayruha.mexc.constant.GlobalParams;
import trade.wayruha.mexc.enums.OrderBookDepth;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static trade.wayruha.mexc.constant.GlobalParams.WEB_SOCKET_KEEP_ALIVE_TOPIC_PERIOD_SEC;
import static trade.wayruha.mexc.util.TestConstants.*;

public class PublicWSConnectionTest {
    final MexcConfig config = new MexcConfig();
    final WSClientFactory factory = new WSClientFactory(config);
    final AtomicInteger wsOpenCounter = new AtomicInteger();
    final AtomicInteger wsResponseCounter = new AtomicInteger();

    @Test
    public void test_OrderBookSubscription() {
        final Set<String> symbols = Set.of(BTC_USD_PAIR_SYMBOL, ETH_USD_PAIR_SYMBOL);
        final Callback callback = new Callback();
        final WebSocketClient ws = factory.orderBookSubscription(symbols, OrderBookDepth.TOP5, callback);
        sleep(GlobalParams.WEB_SOCKET_RECONNECTION_DELAY_MS);
        assertTrue(wsOpenCounter.get() > 0);
        assertTrue(wsResponseCounter.get() > 0);
    }

    @Test
    public void test_noTradesSubscriptionKeepUp() {
        final Set<String> symbols = Set.of(UNUSED_PAIR_SYMBOL);
        final Callback callback = new Callback();
        final WebSocketClient ws = factory.tradesSubscription(symbols, callback);
        int keepAlivePeriodSec = (int)(WEB_SOCKET_KEEP_ALIVE_TOPIC_PERIOD_SEC * 1.2); //+20%
        sleep(keepAlivePeriodSec  * 1000);
        assertTrue(wsOpenCounter.get() > 0);
        assertEquals(2, wsResponseCounter.get());
    }

    @Test
    public void test_TradesSubscription() {
        final Set<String> symbols = Set.of(BTC_USD_PAIR_SYMBOL, ETH_USD_PAIR_SYMBOL);
        final Callback callback = new Callback();
        final WebSocketClient ws = factory.tradesSubscription(symbols, callback);
        sleep(GlobalParams.WEB_SOCKET_RECONNECTION_DELAY_MS);
        assertTrue(wsOpenCounter.get() > 0);
        assertTrue(wsResponseCounter.get() > 0);
    }

    @Test
    public void test_DiffDepthSubscription() {
        final Set<String> symbols = Set.of(BTC_USD_PAIR_SYMBOL, ETH_USD_PAIR_SYMBOL);
        final Callback callback = new Callback();
        final WebSocketClient ws = factory.diffDepthSubscription(symbols, callback);
        sleep(GlobalParams.WEB_SOCKET_RECONNECTION_DELAY_MS);
        assertTrue(wsOpenCounter.get() > 0);
        assertTrue(wsResponseCounter.get() > 0);
    }

    @SneakyThrows
    private static void sleep(int timeMs){
        Thread.sleep(timeMs);
    }

    class Callback implements WebSocketCallback{

        @Override
        public void onResponse(Object response) {
            System.out.println("Got response:" + response);
            wsResponseCounter.incrementAndGet();
        }

        @Override
        public void onClosed(int code, String reason) {
            WebSocketCallback.super.onClosed(code, reason);
        }

        @Override
        public void onFailure(Throwable ex, Response response) {
            WebSocketCallback.super.onFailure(ex, response);
        }

        @Override
        public void onOpen(Response response) {
            WebSocketCallback.super.onOpen(response);
            wsOpenCounter.incrementAndGet();
        }
    }
}
