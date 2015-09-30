// /*
// * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
// * 山东蚁巡网络科技有限公司。保留所有权利
// */
// package cjy.com.kafka;
//
// import java.nio.ByteBuffer;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import kafka.api.FetchRequest;
// import kafka.api.FetchRequestBuilder;
// import kafka.api.PartitionOffsetRequestInfo;
// import kafka.common.ErrorMapping;
// import kafka.common.TopicAndPartition;
// import kafka.javaapi.FetchResponse;
// import kafka.javaapi.OffsetResponse;
// import kafka.javaapi.PartitionMetadata;
// import kafka.javaapi.TopicMetadata;
// import kafka.javaapi.TopicMetadataRequest;
// import kafka.javaapi.consumer.SimpleConsumer;
// import kafka.message.MessageAndOffset;
//
// import org.apache.commons.beanutils.BeanUtils;
//
// /**
// * <p>功能描述,该部分必须以中文句号结尾。<p>
// *
// * 创建日期 2014年12月24日<br>
// * @author $Author$<br>
// * @version $Revision$ $Date$
// * @since 3.0.0
// */
// public class UserSimpleConsumer {
// public static void main(String args[]) {
// UserSimpleConsumer example = new UserSimpleConsumer();
// long maxReads = 100;
// String topic = "test-user-001";
// int partition = 0; //
// // int partition = 1; //
// List<String> seeds = new ArrayList<String>();
// seeds.add("rs229");
// seeds.add("rs227");
// seeds.add("rs226");
// seeds.add("rs198");
// seeds.add("rs197");
// int port = Integer.parseInt("9092");
// try {
// example.run(maxReads, topic, partition, seeds, port);
// } catch (Exception e) {
// System.out.println("Oops:" + e);
// e.printStackTrace();
// }
// }
//
// private List<String> m_replicaBrokers = new ArrayList<String>();
//
// public UserSimpleConsumer() {
// m_replicaBrokers = new ArrayList<String>();
// }
//
// public void run(long a_maxReads, String a_topic, int a_partition, List<String> a_seedBrokers, int a_port)
// throws Exception {
// // find the meta data aboutthe topic and partition we are interested in
// //
// PartitionMetadata metadata = findLeader(a_seedBrokers, a_port, a_topic, a_partition);
// if (metadata == null) {
// System.out.println("Can'tfind metadata for Topic and Partition. Exiting");
// return;
// }
// if (metadata.leader() == null) {
// System.out.println("Can'tfind Leader for Topic and Partition. Exiting");
// return;
// }
// String leadBroker = metadata.leader().host();
// String clientName = "Client_" + a_topic + "_" + a_partition;
// SimpleConsumer consumer = new SimpleConsumer(leadBroker, a_port, 100000, 64 * 1024, clientName);
// long readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.EarliestTime(),
// clientName);
// int numErrors = 0;
// while (a_maxReads > 0) {
// if (consumer == null) {
// consumer = new SimpleConsumer(leadBroker, a_port, 100000, 64 * 1024, clientName);
// }
// FetchRequest req = new FetchRequestBuilder().clientId(clientName)
// .addFetch(a_topic, a_partition, readOffset, 100000).build(); // Note: this fetchSize of 100000 might
// // needto
// // be increased if large batches are written to
// // Kafka
// FetchResponse fetchResponse = consumer.fetch(req);
// if (fetchResponse.hasError()) {
// numErrors++;
// // Something wentwrong!
// short code = fetchResponse.errorCode(a_topic, a_partition);
// System.out.println("Errorfetching data from the Broker:" + leadBroker + " Reason: " + code);
// if (numErrors > 5)
// break;
// if (code == ErrorMapping.OffsetOutOfRangeCode()) {
// // We asked for aninvalid offset. For simple case ask for
// // the last elementto reset
// readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.LatestTime(),
// clientName);
// continue;
// }
// consumer.close();
// consumer = null;
// leadBroker = findNewLeader(leadBroker, a_topic, a_partition, a_port);
// continue;
// }
// numErrors = 0;
// long numRead = 0;
// for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(a_topic, a_partition)) {
// long currentOffset = messageAndOffset.offset();
// if (currentOffset < readOffset) {
// System.out.println("Foundan old offset: " + currentOffset + " Expecting: " + readOffset);
// continue;
// }
// readOffset = messageAndOffset.nextOffset();
// ByteBuffer payload = messageAndOffset.message().payload();
// byte[] bytes = new byte[payload.limit()];
// payload.get(bytes);
// // === 这里就是反序列化=======================================================
// User user = (User) BeanUtils.bytes2Object(bytes);
// System.out.println(String.valueOf(messageAndOffset.offset()) + ": " + user);
// // =========================================================================
// numRead++;
// a_maxReads--;
// }
// if (numRead == 0) {
// try {
// Thread.sleep(1000);
// } catch (InterruptedException ie) {}
// }
// }
// if (consumer != null)
// consumer.close();
// }
//
// public static long getLastOffset(SimpleConsumer consumer, String topic, int partition, long whichTime,
// String clientName) {
// TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
// Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition,
// PartitionOffsetRequestInfo>();
// requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
// kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo,
// kafka.api.OffsetRequest.CurrentVersion(), clientName);
// OffsetResponse response = consumer.getOffsetsBefore(request);
// if (response.hasError()) {
// System.out.println("Errorfetching data Offset Data the Broker. Reason: "
// + response.errorCode(topic, partition));
// return 0;
// }
// long[] offsets = response.offsets(topic, partition);
// return offsets[0];
// }
//
// private String findNewLeader(String a_oldLeader, String a_topic, int a_partition, int a_port) throws Exception {
// for (int i = 0; i < 3; i++) {
// boolean goToSleep = false;
// PartitionMetadata metadata = findLeader(m_replicaBrokers, a_port, a_topic, a_partition);
// if (metadata == null) {
// goToSleep = true;
// } else if (metadata.leader() == null) {
// goToSleep = true;
// } else if (a_oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {
// // first time throughif the leader hasn't changed give
// // ZooKeeper a secondto recover
// // second time, assumethe broker did recover before failover,
// // or it was anon-Broker issue
// //
// goToSleep = true;
// } else {
// return metadata.leader().host();
// }
// if (goToSleep) {
// try {
// Thread.sleep(1000);
// } catch (InterruptedException ie) {}
// }
// }
// System.out.println("Unableto find new leader after Broker failure. Exiting");
// throw new Exception("Unable to findnew leader after Broker failure. Exiting");
// }
//
// private PartitionMetadata findLeader(List<String> a_seedBrokers, int a_port, String a_topic, int a_partition) {
// PartitionMetadata returnMetaData = null;
// loop: for (String seed : a_seedBrokers) {
// SimpleConsumer consumer = null;
// try {
// consumer = new SimpleConsumer(seed, a_port, 100000, 64 * 1024, "leaderLookup");
// List<String> topics = Collections.singletonList(a_topic);
// TopicMetadataRequest req = new TopicMetadataRequest(topics);
// kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);
// List<TopicMetadata> metaData = resp.topicsMetadata();
// for (TopicMetadata item : metaData) {
// for (PartitionMetadata part : item.partitionsMetadata()) {
// if (part.partitionId() == a_partition) {
// returnMetaData = part;
// break loop;
// }
// }
// }
// } catch (Exception e) {
// System.out.println("Errorcommunicating with Broker [" + seed + "] tofind Leader for [" + a_topic + ", "
// + a_partition + "] Reason: " + e);
// } finally {
// if (consumer != null)
// consumer.close();
// }
// }
// if (returnMetaData != null) {
// m_replicaBrokers.clear();
// for (kafka.cluster.Replica replica : returnMetaData.replicas()) {
// m_replicaBrokers.add(replica.host());
// }
// }
// return returnMetaData;
// }
// }
