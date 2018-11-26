# Robo
Robo Movement

#REST APIs

#Report
URL: {{host}}/api/tabletop/report 
Method: GET
Response Body: String. "X,Y,FACE" as Str 4ex:2,3,NORTH
Response Codes: Always 200

#PLACE
URL: {{host}}/api/tabletop/place?x=2&y=3&face=EAST
Method: PUT
Response Body: EMPTY
Response Codes:
- 200 If valid position & face.
- 202 , If invalid position, 0 <= X&Y < 5 (MAX_UNITS)
- 400 , If invalid face, it should be one of [NORTH,SOUTH,EAST,WEST]

#MOVE
URL: {{host}}/api/tabletop/move
Method: PUT
Response Body: EMPTY
Response Codes:
- 200 If new position is valid
- 202 If new position is valid or robot is not placed yet

#LEFT
URL: {{host}}/api/tabletop/left
Method: PUT
Response Body: EMPTY
Response Codes:
- 200 If rotated successfully
- 202 If robot is not placed yet

#RIGHT
URL: {{host}}/api/tabletop/right
Method: PUT
Response Body: EMPTY
Response Codes:
- 200 If rotated successfully
- 202 If robot is not placed yet
