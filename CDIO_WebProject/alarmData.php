<?phpinclude 'Connection.php';$alarmArray = array();$conn = connect();$query = sprintf("call Get_All_Alarms_By_Dep('%s')", $_POST["dep"]);$result = $conn->query($query);while ($row = $result->fetch_assoc()) {    $alarmInfoObject = (object)[        'alarmID' => (string)$row['Alarm_ID'],        'Date' => (string)$row['DateTime'],        'SensorID' => (string)$row['Sensor_ID'],        'reason' => (string)$row['Reason'],        'Value' => (string)$row['Value'],        'ApplianceID' => (string)$row['Appliance_ID'],    ];    $alarmArray[] = $alarmInfoObject;}$conn->close();header("Content-Type: application/json;charset=utf-8");echo json_encode($alarmArray);