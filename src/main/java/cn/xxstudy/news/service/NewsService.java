package cn.xxstudy.news.service;

import cn.xxstudy.news.proto.NewsProto;
import cn.xxstudy.news.proto.NewsServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Random;

/**
 * @date: 2023/3/27 20:48
 * @author: TangRen
 * @remark:
 */
public class NewsService extends NewsServiceGrpc.NewsServiceImplBase {

    @Override
    public void list(NewsProto.NewsRequest request, StreamObserver<NewsProto.NewsResponse> responseObserver) {
        String date = request.getDate();
        try {
            NewsProto.NewsResponse.Builder builder = NewsProto.NewsResponse.newBuilder();
            for (int i = 0; i < 100; i++) {
                NewsProto.News news = NewsProto.News.newBuilder()
                        .setId(i)
                        .setContent(String.format("%s%s%d%d", date, "当日新闻内容：", new Random().nextInt(100), System.currentTimeMillis()))
                        .setCreateTime(System.currentTimeMillis())
                        .setTitle("新闻标题：" + i)
                        .build();
                builder.addNews(news);
            }
            responseObserver.onNext(builder.build());
        } catch (Exception e) {
            responseObserver.onError(e);
        }
        responseObserver.onCompleted();
    }
}
