// /*
// * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License");
// you
// * may not use this file except in compliance with the License. You may obtain a copy of the License at
// * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
// * either express or implied. See the License for the specific language governing permissions and limitations under
// the
// * License.
// */
// import java.io.Serializable;
//
// /**
// * <p>功能描述,该部分必须以中文句号结尾。<p>
// *
// * 创建日期 2012-10-17<br>
// * @author $Author$<br>
// * @version $Revision$ $Date$
// * @since 3.0.0
// */
// public class TopoStartScanCommand extends AgentCommand {
// //
// private static final long serialVersionUID = 8543336267161787338L;
// /* 获取topo扫描参数 */
// private final TopoScan_args args = new TopoScan_args();
//
// /**
// * 命令名称
// */
// public String getName() {
// return "startscan";
// }
//
// /**
// * 命令所属的组
// */
// public String getGroup() {
// return "topo";
// }
//
// public String getShortDescription() {
// return "启动Topo扫描";
// }
//
// public Serializable getCommandArgs() {
// return this.args;
// }
//
// public String toString() {
// return this.getAgents() + this.getGroup() + ":" + this.getName() + this.args;
// }
// }
