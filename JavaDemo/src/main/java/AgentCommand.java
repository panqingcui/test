import java.io.Serializable;
import java.util.List;

/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
/**
 * A class encapsulating all the data necessary to invoke a command on a remote agent.
 */
public class AgentCommand implements Command {
    private int agentVersion;
    private int cmdVersion;
    private String cmd;
    private AgentRemoteValue cmdArg;

    public AgentCommand(int agentVersion, int cmdVersion, String cmd, AgentRemoteValue cmdArg) {
        this.agentVersion = agentVersion;
        this.cmdVersion = cmdVersion;
        this.cmd = cmd;
        this.cmdArg = cmdArg;
    }

    public int getAgentVersion() {
        return this.agentVersion;
    }

    public int getCommandVersion() {
        return this.cmdVersion;
    }

    public String getCommand() {
        return this.cmd;
    }

    public AgentRemoteValue getCommandArg() {
        return this.cmdArg;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getGroup() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addAgent(String agentID) {
        // TODO Auto-generated method stub
    }

    @Override
    public List getAgents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addServer(String serverID) {
        // TODO Auto-generated method stub
    }

    @Override
    public List getServers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getShortDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Serializable getCommandArgs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSensorToken() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSensorToken(String sensorToken) {
        // TODO Auto-generated method stub
    }
}
