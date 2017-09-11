<?php
header("Content-Type: application/json;charset=utf-8");

include 'Connection.php';

$sensorArray = array();

$conn = connect();


if (!empty($_POST['sensorID'])) {

    $sensorID = $_POST["sensorID"];
    $queryLimit = $_POST["backlog"];


    $query = sprintf("call Get_Sensor_Value_By_Date('%s','%s' )", $sensorID, $queryLimit);

    $result = $conn->query($query);


    while ($row = $result->fetch_assoc()) {

        $sensorInfoObject = (object)[
            'Value' => (string)$row['Value'],
            'Date' => (string)$row['DateTime'],
        ];

        $sensorArray[] = $sensorInfoObject;

    }


} elseif (!empty($_POST['dep'])) {

    $department = $_POST["dep"];

    $query = sprintf("call Get_All_Sensors_By_Dep('%s')", $department);

    $result = $conn->query($query);

    while ($row = $result->fetch_assoc()) {

        $sensorInfoObject = (object)[
            'sensorID' => (string)$row['Sensor_ID'],
            'Type' => (string)$row['Type'],
            'Department' => (string)$row['Department_ID'],
            'ApplianceID' => (string)$row['Appliance_ID'],
            'Value' => (string)$row['Value'],
            'Group'
        ];

        $sensorArray[] = $sensorInfoObject;

    }
} else {

    $query = sprintf("SELECT * FROM current_sensor_data");

    $result = $conn->query($query);

    while ($row = $result->fetch_assoc()) {

        $sensorInfoObject = (object)[
            'sensorID' => (string)$row['Sensor_ID'],
            'Type' => (string)$row['Type'],
            'Department' => (string)$row['Department_ID'],
            'ApplianceID' => (string)$row['Appliance_ID'],
            'Value' => (string)$row['Value'],
            'Group'
        ];

        $sensorArray[] = $sensorInfoObject;

    }
}


$conn->close();

echo json_encode($sensorArray);

