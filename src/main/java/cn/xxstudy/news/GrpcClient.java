package cn.xxstudy.news;

import cn.xxstudy.news.proto.NewsProto;
import cn.xxstudy.news.proto.NewsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.Scanner;

/**
 * @date: 2023/3/27 21:02
 * @author: TangRen
 * @remark:
 */
public class GrpcClient {
    public static final String host = "localhost";
    public static final int port = 9090;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        NewsServiceGrpc.NewsServiceBlockingStub blockingStub = NewsServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String next = scanner.next();
            if ("-1".equals(next)) {
                channel.shutdown();
                return;
            }
            NewsProto.NewsRequest request = NewsProto.NewsRequest.newBuilder().setDate(scanner.nextLine()).build();
            NewsProto.NewsResponse response = blockingStub.list(request);

            List<NewsProto.News> newsList = response.getNewsList();
            newsList.forEach((news -> System.out.println("createTime=" + news.getCreateTime() + ",content=" + news.getContent() + ",id=" + news.getId())));

        }


    }
}
